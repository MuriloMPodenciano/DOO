package ifsp.doo.atas.domain.DTO.encerramento;

import java.time.LocalDateTime;

public record EncerramentoPostRequestDTO(String textoEncerramento, String local, LocalDateTime horaEncerramento, String nomeRedator) {
    
}
