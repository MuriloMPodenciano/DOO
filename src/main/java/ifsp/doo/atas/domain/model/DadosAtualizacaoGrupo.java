package ifsp.doo.atas.domain.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizacaoGrupo(
        @NotNull
        Long id,
        String nome,
        List<Pessoa> funcionarios,
        @NotNull
        boolean status
) {
}
