package imd.urfn.br.emailagent.domain;

import java.util.UUID;

public record UpdateContract(
        Contrato contrato,
        String status,
        UUID relatedContractID
) {
}
