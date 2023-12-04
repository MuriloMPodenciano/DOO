package ifsp.doo.atas.domain.DTO.pauta;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record PautaPutRequestDTO(Long id, String pauta, PessoaGetResponseDTO pessoa, String decisao) {
    
}
