package imd.urfn.br.emailagent.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ContractResponseDTO {
    private UUID id;
    private String jsonData;
    private String emailThreadId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String emptyFields;

    public String getEmptyFields() {
        return emptyFields;
    }

    public void setEmptyFields(String emptyFields) {
        this.emptyFields = emptyFields;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getEmailThreadId() {
        return emailThreadId;
    }

    public void setEmailThreadId(String emailThreadId) {
        this.emailThreadId = emailThreadId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

