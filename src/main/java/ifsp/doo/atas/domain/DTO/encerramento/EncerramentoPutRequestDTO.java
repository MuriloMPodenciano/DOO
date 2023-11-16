package ifsp.doo.atas.domain.DTO.encerramento;

import java.time.LocalDateTime;

public record EncerramentoPutRequestDTO(String textoEncerramento, String local, LocalDateTime horaEncerramento, String nomeRedator) {
    
}
