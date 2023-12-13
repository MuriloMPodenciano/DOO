package ifsp.doo.atas.domain.DTO.discussao;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record DiscussaoPutRequestDTO(Long id, String discussao, PessoaGetResponseDTO pessoa) {
    
}
