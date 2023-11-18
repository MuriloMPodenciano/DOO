package ifsp.doo.atas.domain.DTO.pauta;

public record PautaGetResponseDTO(Long id, String nome, String descricao) {
    public PautaGetResponseDTO(PautaGetPersistDTO pauta) {
        this(pauta.id(), pauta.nome(), pauta.descricao());
    }
}
