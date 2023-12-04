package ifsp.doo.atas.domain.DTO.pessoa;

public record PessoaGetResponseDTO(Long id, String nome, String email, String cargo, Boolean status) {
    public PessoaGetResponseDTO(PessoaGetPersistDTO pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getCargo(), pessoa.getStatus());
    }
}
