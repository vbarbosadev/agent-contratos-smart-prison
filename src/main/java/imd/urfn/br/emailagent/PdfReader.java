package imd.urfn.br.emailagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PdfReader {

    final int BATCH_SIZE = 20;
    final int PAUSE_SECONDS = 60;

    private final static Logger LOG = LoggerFactory.getLogger(PdfReader.class);



    @Autowired
    VectorStore vectorStore;

    public void load(String path) {



        Resource pdf = new ClassPathResource(path);
        String fileName = pdf.getFilename();

        if (fileName == null) {
            LOG.error("Não foi possível obter o nome do arquivo do caminho: {}", path);
            return;
        }

        LOG.info("Verificando se o documento '{}' já existe no Vector Store...", fileName);

        SearchRequest request = SearchRequest.builder()
                .query(" ")
                .topK(1)
                .filterExpression("file_name == '%s'".formatted(fileName))
                .build();

        List<Document> existingDocuments = vectorStore.similaritySearch(request);

        if (!existingDocuments.isEmpty()) {
            LOG.info("O documento '{}' já foi processado e se encontra no Vector Store. O carregamento será ignorado.", fileName);
            return;
        }


        LOG.info("Iniciando pdf");
        var config = PdfDocumentReaderConfig.builder()
                .withPageExtractedTextFormatter(
                        new ExtractedTextFormatter.Builder()
                                .build())
                .build();

        var pdfReader = new PagePdfDocumentReader(pdf, config);
        var textSplitter = new TokenTextSplitter();

        List<Document> allTextChunks = textSplitter.apply(pdfReader.get());
        int totalChunks = allTextChunks.size();


        LOG.info("Iniciando salvamento do pdf em lote de chunks");

        for (int i = 0; i < totalChunks; i+= BATCH_SIZE) {
            int endIndex = Math.min(totalChunks, i + BATCH_SIZE);
            List<Document> batch = allTextChunks.subList(i, endIndex);

            LOG.info("Processando lote de chunks: {} a {}...", i+ 1, endIndex);

            try {
                vectorStore.add(batch);
                LOG.info("Lote enviado com sucesso.");

                if (endIndex < totalChunks) {
                    LOG.info("Pausando por {} segundos",  PAUSE_SECONDS);
                    TimeUnit.SECONDS.sleep(PAUSE_SECONDS);
                }

            } catch (InterruptedException e) {
                LOG.error("O processo foi interrrompido durante a pausa.", e);
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                LOG.error("O processo foi interrompido.", e);
            }

        }

        LOG.info("PDF read complete");

    }


}
