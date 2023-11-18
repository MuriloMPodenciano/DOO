package ifsp.doo.atas.domain.model;

import ifsp.doo.atas.domain.DTO.informe.InformeGetPersistDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Informe {
    private Long id;
    private String nome;
    private String descricao;

    public Informe(String nome, String descricao) {
        this(null, nome, descricao);
    }

    public Informe(InformePostRequestDTO dto) {
        this(dto.nome(), dto.descricao());
    }

    public Informe(InformeGetPersistDTO dto) {
        this(dto.id(), dto.nome(), dto.descricao());
    }
}
