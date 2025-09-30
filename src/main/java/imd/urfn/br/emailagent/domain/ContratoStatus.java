package imd.urfn.br.emailagent.domain;

public record ContratoStatus(
        FinalCheck status,
        Contrato contrato
) {
}
