package imd.urfn.br.emailagent;

import com.embabel.agent.config.annotation.EnableAgents;

import com.embabel.agent.core.ProcessOptions;
import com.embabel.agent.core.Verbosity;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SpringBootApplication
@EnableAgents

public class EmailAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailAgentApplication.class, args);
    }



}
