package imd.urfn.br.emailagent.domain;

public record ContratoStatusDTO(
        FinalCheck status,
        ContratoDTO contrato
) {
}
