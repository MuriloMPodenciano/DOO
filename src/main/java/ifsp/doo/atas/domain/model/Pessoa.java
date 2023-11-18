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
            pessoaBanco.id(),
            pessoaBanco.nome(),
            new Email(pessoaBanco.email()),
            pessoaBanco.cargo(),
            pessoaBanco.status()
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
}

/*
 * changes:
 * - adicionando o lombokinha
 * - remoção de construtores desnecessarios e poluentes
 * - criação do objeto Email e adequando as entradas e saidas do tipo
 * - como dito no controller, o usuario escolhe o novo valor do status e
 *     se vier o mesmo valor, isso não será considerado um erro. isso
 *     dispensa o método mudarStatus
 * - colocando o método de atualizar pessoa nos conformes do documento
 *     (somente cargo e status mudam)
 * - até o presente momento, retirei os métodos de remover coisa
 */
