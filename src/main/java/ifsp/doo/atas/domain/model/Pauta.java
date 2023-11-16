package ifsp.doo.atas.domain.model;

import ifsp.doo.atas.domain.DTO.pauta.PautaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;

public class Pauta {
    private Long id;
    private String nome;
    private String descricao;

    public Pauta(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Pauta(PautaPostRequestDTO dto) {
        this(dto.id(), dto.nome(), dto.descricao());
    }

    public Pauta(PautaGetPersistDTO dto) {
        this(dto.id(), dto.nome(), dto.descricao());
    }
}
