package imd.urfn.br.emailagent;

import com.embabel.agent.core.Agent;
import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.core.AgentProcess;
import com.embabel.agent.core.ProcessOptions;
import com.embabel.agent.domain.io.UserInput;
import imd.urfn.br.emailagent.domain.EmailResponse;
import imd.urfn.br.emailagent.dto.EmailCallbackRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AgentController {

    private final ApiCallInterface apiCall;

    private final AgentPlatform agentPlatform;

    public AgentController(ApiCallInterface apiCall, AgentPlatform agentPlatform) {
        this.apiCall = apiCall;
        this.agentPlatform = agentPlatform;
    }

    @PostMapping("/updateEmail")
    public String updateEmail(@RequestBody EmailResponse email, Model model) {
        Agent agent = agentPlatform.agents().stream()
                .filter(a -> a.getName().toLowerCase().contains("update"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no agent found"));

        AgentProcess agentProcess = agentPlatform.createAgentProcessFrom(
                agent,
                ProcessOptions.DEFAULT,
                email
        );

        model.addAttribute("EmailResponse", email);

        new GenericProcessingValues(
                agentProcess,
                "Update contract with email answer",
                email.body(),
                "FinalCheck",
                "contrato"
        ).addToModel(model);

        agentProcess.run();

        return agentProcess.toString();
    }

    @PostMapping("/plan")
    public String plan(Model model) {
        String call = "Get the contract based on this pdf: " + apiCall.get();

        System.out.println("call: " + call);

        UserInput input =  new UserInput(call);

        Agent agent = agentPlatform.agents().stream()
                .filter(a -> a.getName().toLowerCase().contains("agent"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        AgentProcess agentProcess = agentPlatform.createAgentProcessFrom(
                agent,
                ProcessOptions.DEFAULT,
                call, input
        );

        model.addAttribute("callToAgent", call);
        new GenericProcessingValues(
                agentProcess,
                "Searching your contract",
                call,
                "contrato",
                "contrato"
        ).addToModel(model);

          agentProcess.run();


        return agentProcess.toString();

    }

    @PostMapping("/callback")
    public ResponseEntity<String> handleEmailCallback(@RequestBody EmailCallbackRequestDTO callback, Model model) {
        System.out.println("Recebi resposta do e-mail:");
        System.out.println("ThreadId: " + callback.getThreadId());
        System.out.println("Mensagem: " + callback.getMessage());
        System.out.println("RelactedId: " + callback.getRelatedContractId());

        Agent agent = agentPlatform.agents().stream()
                .filter(a -> a.getName().toLowerCase().contains("update"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no agent found"));

        UserInput input = new UserInput(callback.toString());

        AgentProcess agentProcess = agentPlatform.createAgentProcessFrom(
                agent,
                ProcessOptions.DEFAULT,
                callback
        );

        model.addAttribute("EmailCallback", callback);

        new GenericProcessingValues(
                agentProcess,
                "Update contract with email answer",
                 callback.getMessage(),
                "FinalCheck",
                "contrato"
        ).addToModel(model);

        agentProcess.run();



        // Aqui você "acorda" o agente, usando o threadId + mensagem como contexto
        // Pode disparar uma task assíncrona ou chamar outro service

        return ResponseEntity.ok(agentProcess.toString());
    }
}




