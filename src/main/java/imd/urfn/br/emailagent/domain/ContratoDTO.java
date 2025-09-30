package imd.urfn.br.emailagent.domain;

import java.time.LocalDate;

public record ContratoDTO(
        Empresa empresa,
        String orgaoId,
        Long valorCentavosContrato,
        LocalDate dataInicioVigencia,
        LocalDate dataFimVigencia,
        String objetoContrato,
        String localTrabalho,
        String tipoTrabalho,
        String cargaHoraria,
        VagasList vagas
) {
}
