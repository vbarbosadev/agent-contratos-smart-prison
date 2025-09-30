package imd.urfn.br.emailagent.domain;

import java.util.List;

public record FinalCheckDTO(
        Integer contratoId,
        String status,
        List<String> fieldsEmpty
) {
}
