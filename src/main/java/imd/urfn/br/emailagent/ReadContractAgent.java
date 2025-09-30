package imd.urfn.br.emailagent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;
import com.embabel.agent.domain.io.UserInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import imd.urfn.br.emailagent.domain.*;
import imd.urfn.br.emailagent.dto.ContractRequestDTO;
import imd.urfn.br.emailagent.dto.EmailRequestDTO;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Agent(
        name = "Contract agent",
        description = "Create a Contrato based on path from user input")
public class ReadContractAgent {

    @Autowired
    VectorStore vectorStore;
    private final PdfReader pdfReader;
    private final ApiCallInterface apiCall;


    public ReadContractAgent(PdfReader pdfReader, ApiCallInterface apiCall) {
        this.pdfReader = pdfReader;
        this.apiCall = apiCall;
    }

    @Action(
            description = "Find the Path to using to do a embedding and get filename to use in searches")
    public Path getPath(UserInput userInput, OperationContext context){
        String prompt = """
                With the user input, get the path or url and create a path object.
                If the value is a url, put it in url field and set path to null
                But if the value is a local path, put in path field and set url to null
                
                get the file name and put in `namefile` field
                
                null is really null, not a String
                ==========
                userInput: '%s'
                ==========
                
                """;

        return context.ai()
                .withAutoLlm()
                .createObject(
                        prompt.formatted(userInput),
                        Path.class
                );
    }

    @Action(description = "Create a enterprise(Empresa)")
    public Empresa getEnterprise(Path path, OperationContext context){

        SearchRequest request = SearchRequest.builder()
                .query("Empresa, contato, endereço, Nome da empresa, Serviço, Email, Ramo/Atividade")
                .filterExpression("file_name == '%s'".formatted(path.filename()))
                .build();

        List<Document> information = vectorStore.similaritySearch(request);

        String prompt = """
                Using this {information}: search and create a enterprise(Empresa) with the data.
                The enterprise is related with the enterprise will hire the inmates to work in the vacancies
                
                obs: the email should be ignored, and email field should be set how "vbarbosa.mg@gmail.com"
                obs: if any field is empty or null, replace with the String: "não encontrado no documento"
                obs: use this model to search a infos about the enterprise

                ==================
                begin of enterprise model
                ==================
                "empresa": {
                "razaoSocial": "",
                "nome": "",
                "cnpj": "",
                "telefone": "",
                "email": "",
                "responsavel": "",
                "logradouro": "",
                "numero": "",
                "complemento": "",
                "bairro": "",
                "cep": "",
                "cidade": "",
                "uf": "",
                "observacoes": "" }
                ==================
                end of enterprise model
                ==================

              
                ==================
                begin of information
                ==================
                '%s'
                ==================
                end of information
                ==================

                """;

        return context.ai()
                .withAutoLlm()
                .createObject(
                        prompt.formatted(information),
                        Empresa.class
                );
    }



    @Action(
            description = "With filename do a search and get the values to make a list of vacancies(Vagas) ")
    public VagasList getVagas(Path path, OperationContext context) throws Exception {

        SearchRequest request = SearchRequest.builder()
                .query("Vagas disponiveis")
                .filterExpression("file_name == '%s'".formatted(path.filename()))
                .build();

        List<Document> information = vectorStore.similaritySearch(request);

        String prompt = """
                Using a {information}: search the data to generate the vagas; searching values can be used.
                
                obs: the values of json are a example of which data you should be search
                
                ========================
                Begin of vagas model
                ========================
                {
                  "vagas": [
                    {
                      "funcaoId": ,
                      "quantidade":,
                      "remuneracaoCentavos":,
                      "descricaoFuncoes": ""
                    },
                    {
                      "funcaoId": ,
                      "quantidade": ,
                      "remuneracaoCentavos":,
                      "descricaoFuncoes": ""
                    }
                  ]
                }
                ========================
                end of vagas model
                ========================
                
                use this content to find that:
                ==================
                begin of information
                ==================
                '%s'
                ==================
                end of information
                ==================
                """;

        return context.ai()
                .withAutoLlm()
                .createObject(
                        prompt.formatted(information),
                        VagasList.class
                );
    }


    @Action(
            pre = "hasRun_imd.urfn.br.emailagent.AgentTest.getPath",
            description = "Using the filename in Path to search the data to use for complete the contract")
    public Data getData(Path path, OperationContext context) throws Exception {

        SearchRequest request = SearchRequest.builder()
                .query("""
                        Id do orgão do contrato,
                        Valor do Contrato,
                        Data de inicio do contrato (vigência),
                        Data de fim do contrato (Vigência),
                        local de trabalho (dos detentos),
                        Tipos de trabalhos realizados,
                        Carga horária dos trabalhos,
                        Objeto de contrato
                        """)
                .filterExpression("file_name == '%s'".formatted(path.filename()))
                .build();

        List<Document> information = vectorStore.similaritySearch(request);

        String prompt = """
                Using a {information}: search the data can be fill de fields in data model, search only data can be used.

                obs: the values of json are a example of which data you should be search
                
                obs: if you did not find any field, fill this field with a String: "não encontrado no documento" or null if isn't a string
                
                that is the fields cant be a string: orgaoId, valorCentavosContrato, this value need be null if you dont find
                
                obs: in date fields (dataInicioVigencia, dataFimVigencia), the values should be a string with data or "não encontrado no documento" if dont find.

                ========================
                Begin of data model
                ========================
                {
                  "orgaoId": ,
                  "valorCentavosContrato": ,
                  "dataInicioVigencia": "",
                  "dataFimVigencia": "",
                  "localTrabalho": "",
                  "tipoTrabalho": "",
                  "cargaHoraria": "",
                  "objetoContrato": "" }
                ========================
                end of data model
                ========================


                use this content to find that:
                ==================
                begin of information
                ==================
                '%s'
                ==================
                end of information
                ==================

                """;

        return context.ai()
                .withAutoLlm()
                .createObject(
                        prompt.formatted(information),
                        Data.class
                );
    }


    @Action(description = "Create a contract(Contrato) with Data, VagasList, and Empresa ")
    public Contrato makeContract(VagasList vagas, Data data, Empresa empresa, OperationContext context) {

        System.err.println("empresa: \n" +  empresa.toString());
        System.err.println("data:\n " +  data.toString());

        String prompt = """
                Use this values to create a contract
                
                ============
                Empresa
                '%s'
                ============
                ============
                Dados
                '%s'
                ============
                ============
                Vagas
                '%s'
                ============
                """;

        return context.ai()
                .withAutoLlm()
                .createObject(
                        prompt.formatted(empresa.toString(), data.toString(), vagas.toString()),
                        Contrato.class
                );

    }

    @Action
    @AchievesGoal(description = "With contract created, check if all fields is fiiled and return the status of this")
    public ContratoStatus finalCheck(Path path, Contrato contrato, OperationContext context) throws Exception {

        String prompt = """
                
                Search all fields in contract and find if anyone have a field with value equals to: "não encontrado no documento", or is null or empty.
                
                if not, set to "true"

                if yes, set the Status field to "false";
                and set a string like a list with the fields is empty, null/'null' or value is equals to "não encontrado no documento".
                
                
                use this content to find that:
                ==================
                begin of contract
                ==================
                '%s'
                ==================
                end of contract
                ==================
                
                
                """;

        var result = context.ai()
                .withAutoLlm()
                .createObject(
                        prompt.formatted(contrato.toString()),
                        FinalCheck.class
                );

        var conStatus = new ContratoStatus(result, contrato);

        System.out.println("\n\n" + contrato + "\n\n");

        var uuidC = UUID.randomUUID();
        
        String thread_email = "";
        String status = "Completo";
        
        if(result.status().equals("false")){

            status = "Pendente";

            String body = """
                    Preciso dos dados abaixo sobre o contrato:

                    ***********

                    '%s'
                    """;

            var email = new EmailRequestDTO();

            System.err.println(body.formatted(result.fieldsEmpty()));


            email.setBody(body.formatted(result.fieldsEmpty()));
            email.setFrom("agent@gmail.com");
            email.setTo(contrato.getEmpresa().getEmail());
            email.setSubject("Preciso destes dados para finalizar o contrato");
            email.setRelatedContractId(uuidC);
            email.setCallbackUrl("http://localhost:8881/api/callback");

            System.err.println("Email: " + email.toString());
            thread_email = apiCall.email(email);

            System.out.println("TID: " +  thread_email);

        }


        var contratoDTO = new ContractRequestDTO();

        contratoDTO.setJson(contrato.toString());
        contratoDTO.setId(uuidC);
        contratoDTO.setEmail_threadID(thread_email);
        contratoDTO.setStatus(status);

        System.err.println("contrato: " +  contratoDTO.toString());

        apiCall.save(contratoDTO);
        return conStatus;
    }

}
