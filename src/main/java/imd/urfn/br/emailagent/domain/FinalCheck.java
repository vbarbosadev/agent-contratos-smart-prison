package imd.urfn.br.emailagent.domain;

import java.util.List;

public record FinalCheck(
        String status,
        List<String> fieldsEmpty
) {
}
