package imd.urfn.br.emailagent;

import com.embabel.agent.config.models.OpenAiCompatibleModelFactory;
import com.embabel.common.ai.model.EmbeddingService;
import com.embabel.common.ai.model.Llm;
import com.embabel.common.ai.model.PerTokenPricingModel;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class CompatibleModelsConfig extends OpenAiCompatibleModelFactory{

    public CompatibleModelsConfig(
            @Value("${gemini.default-base-url}") String baseUrl,
            @Value("${gemini.default-api-key}") String apiKey,
            @Value("${gemini.models.chat.path}") String completionsPath,
            ObservationRegistry observationRegistry
    ){
        super(baseUrl, apiKey, completionsPath, null, observationRegistry);
    }


    @Bean
    public Llm geminiModel01(
            @Value("${gemini.models.chat.name}") String model
            ){
        return openAiCompatibleLlm(
                model,
                new PerTokenPricingModel(0.40, 1.6),
                "Google AI",
                LocalDate.of(2025, 1, 1)
        );
    }

}
