package imd.urfn.br.emailagent.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class Empresa {

    private final String razaoSocial;
    private final String nomeFantasia;
    private final String cnpj;
    private final String telefone;
    private final String email;
    private final String responsavel;
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cep;
    private final String cidade;
    private final String uf;
    private final String observacoes;

    @JsonCreator
    public Empresa(
            @JsonProperty("razaoSocial") String razaoSocial,
            @JsonProperty("nomeFantasia") String nomeFantasia,
            @JsonProperty("cnpj") String cnpj,
            @JsonProperty("telefone") String telefone,
            @JsonProperty("email") String email,
            @JsonProperty("responsavel") String responsavel,
            @JsonProperty("logradouro") String logradouro,
            @JsonProperty("numero") String numero,
            @JsonProperty("complemento") String complemento,
            @JsonProperty("bairro") String bairro,
            @JsonProperty("cep") String cep,
            @JsonProperty("cidade") String cidade,
            @JsonProperty("uf") String uf,
            @JsonProperty("observacoes") String observacoes
    ) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.responsavel = responsavel;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.observacoes = observacoes;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getObservacoes() {
        return observacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(razaoSocial, empresa.razaoSocial) &&
                Objects.equals(nomeFantasia, empresa.nomeFantasia) &&
                Objects.equals(cnpj, empresa.cnpj) &&
                Objects.equals(telefone, empresa.telefone) &&
                Objects.equals(email, empresa.email) &&
                Objects.equals(responsavel, empresa.responsavel) &&
                Objects.equals(logradouro, empresa.logradouro) &&
                Objects.equals(numero, empresa.numero) &&
                Objects.equals(complemento, empresa.complemento) &&
                Objects.equals(bairro, empresa.bairro) &&
                Objects.equals(cep, empresa.cep) &&
                Objects.equals(cidade, empresa.cidade) &&
                Objects.equals(uf, empresa.uf) &&
                Objects.equals(observacoes, empresa.observacoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(razaoSocial, nomeFantasia, cnpj, telefone, email, responsavel,
                logradouro, numero, complemento, bairro, cep, cidade, uf, observacoes);
    }

    @Override
    public String toString() {
        return "Empresa[" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ']';
    }
}
