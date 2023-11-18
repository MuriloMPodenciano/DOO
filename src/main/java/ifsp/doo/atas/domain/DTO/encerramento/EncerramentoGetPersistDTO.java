package ifsp.doo.atas.domain.DTO.encerramento;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.model.Encerramento;
import jakarta.persistence.Embeddable;

@Embeddable
public record EncerramentoGetPersistDTO(
    String textoEncerramento,
    String local,
    LocalDateTime horaEncerramento,
    String nomeRedator
) {
    public EncerramentoGetPersistDTO(Encerramento encerramento) {
        this(
            encerramento.getTextoEncerramento(),
            encerramento.getLocal(),
            encerramento.getHoraEncerramento(),
            encerramento.getNomeRedator()
        );
    }
}
