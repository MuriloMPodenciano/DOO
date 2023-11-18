package ifsp.doo.atas.domain.model;

import ifsp.doo.atas.domain.DTO.pauta.PautaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {
    private Long id;
    private String nome;
    private String descricao;

    public Pauta(String nome, String descricao) {
        this(null, nome, descricao);
    }

    public Pauta(PautaPostRequestDTO dto) {
        this(dto.nome(), dto.descricao());
    }

    public Pauta(PautaGetPersistDTO dto) {
        this(dto.id(), dto.nome(), dto.descricao());
    }
}
