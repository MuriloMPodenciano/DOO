package ifsp.doo.atas.domain.DTO.discussao;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record DiscussaoGetResponseDTO(Long id, String discussao, PessoaGetResponseDTO pessoa) {
    public DiscussaoGetResponseDTO(DiscussaoGetPersistDTO discussao) {
        this(discussao.getId(), discussao.getDiscussao(), new PessoaGetResponseDTO(discussao.getPessoa()));
    }
}
