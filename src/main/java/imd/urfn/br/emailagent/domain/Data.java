package imd.urfn.br.emailagent.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public final class Data {

    private final String orgaoId;
    private final Long valorCentavosContrato;
    private final String dataInicioVigencia;
    private final String dataFimVigencia;
    private final String localTrabalho;
    private final String tipoTrabalho;
    private final String cargaHoraria;
    private final String objetoContrato;

    @JsonCreator
    public Data(
            @JsonProperty("orgaoId") String orgaoId,
            @JsonProperty("valorCentavosContrato") Long valorCentavosContrato,
            @JsonProperty("dataInicioVigencia") String dataInicioVigencia,
            @JsonProperty("dataFimVigencia") String dataFimVigencia,
            @JsonProperty("localTrabalho") String localTrabalho,
            @JsonProperty("tipoTrabalho") String tipoTrabalho,
            @JsonProperty("cargaHoraria") String cargaHoraria,
            @JsonProperty("objetoContrato") String objetoContrato
    ) {
        this.orgaoId = orgaoId;
        this.valorCentavosContrato = valorCentavosContrato;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
        this.localTrabalho = localTrabalho;
        this.tipoTrabalho = tipoTrabalho;
        this.cargaHoraria = cargaHoraria;
        this.objetoContrato = objetoContrato;
    }

    // Métodos Getters
    public String getOrgaoId() {
        return orgaoId;
    }

    public Long getValorCentavosContrato() {
        return valorCentavosContrato;
    }

    public String getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public String getDataFimVigencia() {
        return dataFimVigencia;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public String getTipoTrabalho() {
        return tipoTrabalho;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public String getObjetoContrato() {
        return objetoContrato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(orgaoId, data.orgaoId) &&
                Objects.equals(valorCentavosContrato, data.valorCentavosContrato) &&
                Objects.equals(dataInicioVigencia, data.dataInicioVigencia) &&
                Objects.equals(dataFimVigencia, data.dataFimVigencia) &&
                Objects.equals(localTrabalho, data.localTrabalho) &&
                Objects.equals(tipoTrabalho, data.tipoTrabalho) &&
                Objects.equals(cargaHoraria, data.cargaHoraria) &&
                Objects.equals(objetoContrato, data.objetoContrato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgaoId, valorCentavosContrato, dataInicioVigencia, dataFimVigencia,
                localTrabalho, tipoTrabalho, cargaHoraria, objetoContrato);
    }

    @Override
    public String toString() {
        return "Data[" +
                "orgaoId='" + orgaoId + '\'' +
                ", valorCentavosContrato=" + valorCentavosContrato +
                ", dataInicioVigencia='" + dataInicioVigencia + '\'' +
                ", dataFimVigencia='" + dataFimVigencia + '\'' +
                ", localTrabalho='" + localTrabalho + '\'' +
                ", tipoTrabalho='" + tipoTrabalho + '\'' +
                ", cargaHoraria='" + cargaHoraria + '\'' +
                ", objetoContrato='" + objetoContrato + '\'' +
                ']';
    }
}