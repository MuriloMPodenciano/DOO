package ifsp.doo.atas.domain.DTO.pauta;

import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.discussao.DiscussaoGetResponseDTO;
import ifsp.doo.atas.domain.model.PautaState;
import ifsp.doo.atas.domain.model.Pessoa;

public record PautaGetResponseDTO(
    Long id,
    String pauta,
    Pessoa pessoa,
    List<DiscussaoGetResponseDTO> discussoes,
    String decisao,
    PautaState estado
) {
    public PautaGetResponseDTO(PautaGetPersistDTO pauta) {
        this(
            pauta.getId(),
            pauta.getPauta(),
            pauta.getPessoa(),
            pauta.getDiscussoes()
                .stream()
                .map(DiscussaoGetResponseDTO::new)
                .collect(Collectors.toList()),
            pauta.getDecisao(),
            pauta.getEstado()
        );
    }
}
