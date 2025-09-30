package imd.urfn.br.emailagent;

import com.knuddels.jtokkit.api.EncodingType;
import org.springframework.ai.embedding.BatchingStrategy;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.TokenCountBatchingStrategy;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {

//    @Bean
//    BatchingStrategy customEmbeddingStrategy() {
//        return new TokenCountBatchingStrategy(
//                EncodingType.CL100K_BASE,
//                8000,
//                0.1
//        );
//    }
//
//    @Bean
//    VectorStore vectorStore(EmbeddingModel embeddingModel) {
//        return SimpleVectorStore.builder(embeddingModel).build();
//    }

    @Bean
    public ApiCallInterface equipamentosHtpp(RestClient.Builder restClientBuilder, @Value("${api.client.base-url}") String baseUrl) {
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
//        RestClient equipamentosClient = restClientBuilder.baseUrl(baseUrl).build();
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(equipamentosClient)).build();
        return factory.createClient(ApiCallInterface.class);
    }





}
