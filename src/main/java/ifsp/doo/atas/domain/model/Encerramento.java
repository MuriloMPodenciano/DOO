package ifsp.doo.atas.domain.model;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoPutRequestDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@AllArgsConstructor
@Getter
public class Encerramento {
    private String textoEncerramento;
    private String local;
    private LocalDateTime horaEncerramento;
    private String nomeRedator;

    public Encerramento(String textoEncerramento, String local, LocalDateTime horaEncerramento) {
        this(textoEncerramento, local, horaEncerramento, Usuario.getNome());
    }

    public Encerramento(EncerramentoPutRequestDTO dto) {
        this(dto.textoEncerramento(), dto.local(), dto.horaEncerramento(), dto.nomeRedator());
    }

    public Encerramento(EncerramentoPostRequestDTO dto) {
        this(dto.textoEncerramento(), dto.local(), dto.horaEncerramento(), dto.nomeRedator());
    }

    public Encerramento(EncerramentoGetPersistDTO dto) {
        this(dto.textoEncerramento(), dto.local(), dto.horaEncerramento(), dto.nomeRedator());
    }
}
