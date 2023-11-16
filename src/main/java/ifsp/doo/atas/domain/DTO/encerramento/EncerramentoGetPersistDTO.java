package ifsp.doo.atas.domain.DTO.encerramento;

import java.time.LocalDateTime;

public record EncerramentoGetPersistDTO(String textoEncerramento, String local, LocalDateTime horaEncerramento, String nomeRedator) {

}
