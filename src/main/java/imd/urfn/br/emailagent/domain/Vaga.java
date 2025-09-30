package imd.urfn.br.emailagent.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class Vaga {

    private final Integer funcaoId;
    private final Integer quantidade;
    private final Long remuneracaoCentavos;
    private final String descricaoFuncoes;

    // Construtor que recebe todos os campos
    @JsonCreator
    public Vaga(
            @JsonProperty("funcaoId") Integer funcaoId,
            @JsonProperty("quantidade") Integer quantidade,
            @JsonProperty("remuneracaoCentavos") Long remuneracaoCentavos,
            @JsonProperty("descricaoFuncoes") String descricaoFuncoes
    ) {
        this.funcaoId = funcaoId;
        this.quantidade = quantidade;
        this.remuneracaoCentavos = remuneracaoCentavos;
        this.descricaoFuncoes = descricaoFuncoes;
    }

    // Getters
    public Integer getFuncaoId() {
        return funcaoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getRemuneracaoCentavos() {
        return remuneracaoCentavos;
    }

    public String getDescricaoFuncoes() {
        return descricaoFuncoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return Objects.equals(funcaoId, vaga.funcaoId) &&
                Objects.equals(quantidade, vaga.quantidade) &&
                Objects.equals(remuneracaoCentavos, vaga.remuneracaoCentavos) &&
                Objects.equals(descricaoFuncoes, vaga.descricaoFuncoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcaoId, quantidade, remuneracaoCentavos, descricaoFuncoes);
    }

    @Override
    public String toString() {
        return "Vaga[" +
                "funcaoId=" + funcaoId +
                ", quantidade=" + quantidade +
                ", remuneracaoCentavos=" + remuneracaoCentavos +
                ", descricaoFuncoes='" + descricaoFuncoes + '\'' +
                ']';
    }
}
