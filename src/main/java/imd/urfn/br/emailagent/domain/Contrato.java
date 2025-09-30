package imd.urfn.br.emailagent.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contrato {

//    private Integer id;

    private Empresa empresa;

    private ContratoStatus statusAtual;

    private String orgaoId;

    private Long valorCentavosContrato;

    private String dataInicioVigencia;

    private String dataFimVigencia;

    private String objetoContrato;

    private String localTrabalho;

    private String tipoTrabalho;

    private String cargaHoraria;

    private VagasList vagas;

    // Construtor padrão
    public Contrato() {
    }

    public Contrato(Empresa empresa, Data data, VagasList vagas) {

        this.empresa = empresa;
        this.vagas = vagas;
        this.orgaoId = data.getOrgaoId();
        this.valorCentavosContrato = data.getValorCentavosContrato();
        this.objetoContrato = data.getObjetoContrato();
        this.localTrabalho = data.getLocalTrabalho();
        this.tipoTrabalho = data.getTipoTrabalho();
        this.cargaHoraria = data.getCargaHoraria();
        this.dataFimVigencia = data.getDataFimVigencia();
        this.dataInicioVigencia = data.getDataInicioVigencia();
    }

//    // Getters e Setters
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }


    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public String getTipoTrabalho() {
        return tipoTrabalho;
    }

    public void setTipoTrabalho(String tipoTrabalho) {
        this.tipoTrabalho = tipoTrabalho;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public ContratoStatus getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(ContratoStatus statusAtual) {
        this.statusAtual = statusAtual;
    }

    public String getOrgaoId() {
        return orgaoId;
    }

    public void setOrgaoId(String orgaoId) {
        this.orgaoId = orgaoId;
    }

    public Long getValorCentavosContrato() {
        return valorCentavosContrato;
    }

    public void setValorCentavosContrato(Long valorCentavosContrato) {
        this.valorCentavosContrato = valorCentavosContrato;
    }

    public String getDataInicioVigencia() {
        return dataInicioVigencia;
    }


    public void setDataInicioVigencia(String dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public void setDataFimVigencia(String dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public String getDataFimVigencia() {
        return dataFimVigencia;
    }



    public String getObjetoContrato() {
        return objetoContrato;
    }

    public void setObjetoContrato(String objetoContrato) {
        this.objetoContrato = objetoContrato;
    }

    public VagasList getVagas() {
        return vagas;
    }

    public void setVagas(VagasList vagas) {
        this.vagas = vagas;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "empresa=" + empresa.toString() +
                ", orgaoId=" + orgaoId +
                ", valorCentavosContrato=" + valorCentavosContrato +
                ", dataInicioVigencia='" + dataInicioVigencia + '\'' +
                ", dataFimVigencia='" + dataFimVigencia + '\'' +
                ", localTrabalho='" + localTrabalho + '\'' +
                ", tipoTrabalho='" + tipoTrabalho + '\'' +
                ", cargaHoraria='" + cargaHoraria + '\'' +
                ", objetoContrato='" + objetoContrato + '\'' +
                ", vagas=" + vagas.vagas().toString() +
                '}';
    }
}