package ifsp.doo.atas.domain.DTO.encerramento;

import java.time.LocalDateTime;

public record EncerramentoGetResponseDTO(
    String textoEncerramento,
    String local,
    LocalDateTime horaEncerramento,
    String nomeRedator
) {
    public EncerramentoGetResponseDTO(EncerramentoGetPersistDTO encerramento) {
        this(
            encerramento.textoEncerramento(),
            encerramento.local(),
            encerramento.horaEncerramento(),
            encerramento.nomeRedator()
        );
    }
}
