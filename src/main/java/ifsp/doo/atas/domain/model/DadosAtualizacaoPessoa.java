package ifsp.doo.atas.domain.model;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPessoa (
        @NotNull Long id,
        String nome,
        String email,
        String cargo
    ){
}
