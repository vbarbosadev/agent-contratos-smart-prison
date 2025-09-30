package imd.urfn.br.emailagent.dto;

import java.util.UUID;

public class EmailCallbackRequestDTO {
    private String threadId;
    private String message;

    public UUID getRelatedContractId() {
        return relatedContractId;
    }

    public void setRelatedContractId(UUID relactedContractId) {
        this.relatedContractId = relactedContractId;
    }

    private UUID relatedContractId;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
