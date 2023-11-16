package ifsp.doo.atas.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record DadosCadastroGrupo(
        @NotNull
        @NotBlank
        @Size(min = 1, max = 255)
        String nome,
        @NotNull
        @Valid
        List<Pessoa> funcionarios
) {
}
