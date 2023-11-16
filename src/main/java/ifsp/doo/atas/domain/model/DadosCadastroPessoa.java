package ifsp.doo.atas.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroPessoa (
    @NotBlank
    String nome,
    @NotBlank String email,
    @NotBlank String cargo,
    @NotNull
    @Valid List<@Valid Long> grupos
){

}
