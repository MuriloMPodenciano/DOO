package ifsp.doo.atas.domain.DTO.pauta;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record PautaPostRequestDTO(String pauta, PessoaGetResponseDTO pessoa) {
}
