package imd.urfn.br.emailagent;

import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;
import com.embabel.agent.domain.io.UserInput;
import imd.urfn.br.emailagent.domain.*;
import imd.urfn.br.emailagent.dto.ContractRequestDTO;
import imd.urfn.br.emailagent.dto.EmailCallbackRequestDTO;
import imd.urfn.br.emailagent.dto.EmailRequestDTO;
import imd.urfn.br.emailagent.dto.ReplyEmailRequestDTO;

import java.util.UUID;

@Agent(name = "update contrato", description = "with the email response, create a contract and check that")
public class UpdateContrato {

    private final ApiCallInterface apiCall;

    public UpdateContrato(ApiCallInterface apiCall) {
        this.apiCall = apiCall;
    }


//
//    @Action(description = "with the EmailResponse, get the contract json to create a contrato object")
//    public Contrato createContract(EmailCallbackRequestDTO email, OperationContext context) {
//
//        System.err.println("c_id" + email.getRelatedContractId());
//        var contratoDTO = apiCall.getContract(email.getRelatedContractId());
//
//        return context.ai()
//                .withAutoLlm()
//                .createObject(
//                        "create a contrato object with the contrato string" +
//                                "\n create the empresa, vagas and the other values based in this: \n'%s".formatted(contratoDTO.getJsonData()),
//                        Contrato.class
//                );
//    }

    @Action(description = "with the original contract and the new datas from email, update a contract and return that")
    public Contrato updateContract(EmailCallbackRequestDTO email, OperationContext context) {

        var contrato = apiCall.getContract(email.getRelatedContractId());

        String prompt = """
                your function is to check the contract and see if the new data is valid for the fields that have the value: "não encontrado no documento", or is null.
                
                if yes, recreate the contract with the original values and add the new values to the fields that are valid.
                if any field with "não encontrado no documento" has not changed, continue with the value that is in the original contract.
                
                obs: Ignore upper and lower case letters, this only considers the object name.

                ==================
                original contract begin
                ==================
                '%s'
                ==================
                original contract ends
                ==================
              
                ==================
                new datas by email begin
                ==================
                '%s'
                ==================
                new datas by email end
                ==================
                """;

        System.err.println("prompt: \n\n" + prompt.formatted(contrato.getJsonData(), email.getMessage()));

        return context.ai()
                .withAutoLlm()
                .createObject(
                        prompt.formatted(contrato.getJsonData(), email.getMessage()),
                        Contrato.class
                );

    }

    @Action(post = "it:imd.urfn.br.emailagent.domain.updateContract")
    @AchievesGoal(description = "after to check the email data and updateContract, check if the contract is really completed and update in database")
    public FinalCheck finalizarContrato(Contrato contrato, EmailCallbackRequestDTO email, OperationContext context) throws Exception {

        System.err.println("Contrato: \n\n" + contrato.toString());
        String prompt = """
                
                Search all fields in contract and find if anyone have a field with value equals to: "não encontrado no documento" or is null/empty.
                
                if not, set Status field to "true"

                if yes, set the Status field to "false";
                and set a string like a list with the fields is empty, null or value is equals to "não encontrado no documento".
                
                
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

        String email_thread = "";
        String status = "Completo";


        if(result.status().equals("false")){

            String body = """
                    Os dados enviados no email anterior não forma suficientes para preencher os dados que faltavam para o contrato
                    
                    =======
                    valores que ainda preciso:
                    '%s'
                    =======
                    """;

            status = "Pendente";


            var emailRequest = new EmailRequestDTO();


            emailRequest.setBody(body.formatted(result.fieldsEmpty()));
            emailRequest.setFrom("agent@gmail.com");
            emailRequest.setTo(contrato.getEmpresa().getEmail());
            emailRequest.setSubject("Preciso de mais dados sobre o contrato");
            emailRequest.setRelatedContractId(email.getRelatedContractId());
            emailRequest.setThreadId(email.getThreadId());
            emailRequest.setCallbackUrl("http://localhost:8881/api/callback");

            apiCall.email(emailRequest);

        }


            var contratoDTO = new ContractRequestDTO();

            contratoDTO.setId(email.getRelatedContractId());
            contratoDTO.setJson(contrato.toString());
            contratoDTO.setEmail_threadID(email.getThreadId());
            contratoDTO.setStatus(status);

            apiCall.save(contratoDTO);

        return result;

    }

}
