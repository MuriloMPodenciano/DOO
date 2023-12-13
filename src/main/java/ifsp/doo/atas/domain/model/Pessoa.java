package ifsp.doo.atas.domain.model;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPutRequestDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Pessoa {
    private Long id;
    private String nome;
    private Email email;
    private String cargo;
    private Boolean status;

    public Pessoa(String nome, Email email, String cargo, Boolean status) {
        this(null, nome, email, cargo, status);
    }

    public Pessoa(PessoaGetPersistDTO pessoaBanco) {
        this(
            pessoaBanco.getId(),
            pessoaBanco.getNome(),
            new Email(pessoaBanco.getEmail()),
            pessoaBanco.getCargo(),
            pessoaBanco.getStatus()
        );
    }

    public Pessoa(PessoaPostRequestDTO pessoaDTO) {
        this(
            pessoaDTO.nome(),
            new Email(pessoaDTO.email()),
            pessoaDTO.cargo(),
            pessoaDTO.status()
        );
    }

    public Pessoa(PessoaGetResponseDTO pessoaDTO) {
        this(
            pessoaDTO.id(),
            pessoaDTO.nome(),
            new Email(pessoaDTO.email()),
            pessoaDTO.cargo(),
            pessoaDTO.status()
        );
    }

    public void atualizarPessoa(PessoaPutRequestDTO pessoaDTO) {
        if (pessoaDTO.cargo() != null && !pessoaDTO.cargo().isBlank())
            cargo = pessoaDTO.cargo();

        status = pessoaDTO.status();
    }

    static Pessoa parsePessoa(PessoaGetResponseDTO pessoa) {
        if (pessoa == null)
            return null;
        try {
            return new Pessoa(pessoa);
        } catch (Exception e) {
            return null;
        }
    }
}
