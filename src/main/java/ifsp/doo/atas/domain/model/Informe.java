package ifsp.doo.atas.domain.model;

import ifsp.doo.atas.domain.DTO.informe.InformesGetPersistDTO;
import ifsp.doo.atas.domain.DTO.informe.InformesPostRequestDTO;

public class Informe {
    private Long id;
    private String nome;
    private String descricao;

    public Informe(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Informe(InformesPostRequestDTO dto) {
        this(dto.id(), dto.nome(), dto.descricao());
    }

    public Informe(InformesGetPersistDTO dto) {
        this(dto.id(), dto.nome(), dto.descricao());
    }
}
