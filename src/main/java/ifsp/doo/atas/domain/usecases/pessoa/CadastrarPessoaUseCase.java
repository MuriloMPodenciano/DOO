package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPostRequestDTO;
import ifsp.doo.atas.domain.model.Email;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CadastrarPessoaUseCase {
    @Autowired
    private PessoaRepository repository;

    public PessoaGetResponseDTO cadastrarPessoa(PessoaPostRequestDTO pessoaDTO) {
        if (repository.existsByEmail(new Email(pessoaDTO.email())))
            throw new DataIntegrityViolationException("Email already exists: " + pessoaDTO.email());

        Pessoa pessoa = new Pessoa(pessoaDTO);

        PessoaGetPersistDTO novaPessoa = new PessoaGetPersistDTO(pessoa);

        return new PessoaGetResponseDTO(repository.save(novaPessoa));
    }
}

/*
 * changes:
 * - eu acho que você confundiu um pouco sobre aonde colocar a anotação @Autowired
 * - UseCases são camadas internas da aplicação, então ela tem acesso ao banco
 *     de dados por si própria, até mesmo pq senão eles não conseguiriam delegar
 *     o fluxo do programa. Ou seja, ele não recebe repository por parametro
 * - ok, é só exluir o construtor e colocar @Autowired em repository...
 *     N. @Autowired e final keyword não combinam juntas, ou seja, tira final
 * - você não precisa verificar a existencia de uma entidade no banco se
 *     o spring já oferece um método que faz isso... aka. getReferenceById()
 * - como eu disse no repository, nome não é uma chave primaria, mas email,
 *     como unique, é boa pra verificar coisas já cadastradas, então eu só
 *     troquei de nome para email, incluso a verificação de email valido
 * - o resto do caminho é o seguinte:  pega os dados do DTO de requisição e 
 *     transforma em uma model para que ela valide os dados, então persiste a
 *     model no banco(passando pra DTO de banco) e envia a resposta
 *     (o recurso criado) em forma de DTO de resposta.
 * - retornando uma String??
 * - @Valid e correlatos da jakarta.validation são fazem sentido aqui pois quem
 *     valida algo é a model, então, no máximo seria lá
 */
