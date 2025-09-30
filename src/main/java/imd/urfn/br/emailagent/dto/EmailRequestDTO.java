package imd.urfn.br.emailagent.dto;

import java.util.UUID;

public class EmailRequestDTO {
    private String threadId;
    private String from;
    private String to;
    private String subject;
    private String body;

    private UUID relatedContractId ;

    public UUID getRelatedContractId() {
        return relatedContractId;
    }

    public void setRelatedContractId(UUID relatedContractId) {
        this.relatedContractId = relatedContractId;
    }

    private String callbackUrl;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getCallbackUrl() { return callbackUrl; }
    public void setCallbackUrl(String callbackUrl) { this.callbackUrl = callbackUrl; }

    @Override
    public String toString() {
        return "EmailSendRequest{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", relatedContractId=" + relatedContractId +
                ", callbackUrl='" + callbackUrl + '\'' +
                '}';
    }
}

