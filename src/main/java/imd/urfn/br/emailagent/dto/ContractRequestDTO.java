package imd.urfn.br.emailagent.dto;

import java.util.UUID;

public class ContractRequestDTO {
    private UUID id;
    private String json;
    private String email_threadID;
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String contract) {
        this.json = contract;
    }

    public String getEmail_threadID() {
        return email_threadID;
    }

    public void setEmail_threadID(String email_threadID) {
        this.email_threadID = email_threadID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContractRequestDTO(){
    }

    @Override
    public String toString() {
        return "ContractRequestDTO{" +
                "id=" + id +
                ", json='" + json + '\'' +
                ", email_threadID='" + email_threadID + '\'' +
                '}';
    }



}
