package ifsp.doo.atas.domain.DTO.informe;

public record InformeGetResponseDTO(Long id, String nome, String descricao) {
    public InformeGetResponseDTO(InformeGetPersistDTO informe) {
        this(informe.id(), informe.nome(), informe.descricao());
    }
}
