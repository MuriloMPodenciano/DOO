package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPutRequestDTO;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EditarPessoaUseCase {
    @Autowired
    private PessoaRepository repository;

    public PessoaGetResponseDTO editarPessoa(PessoaPutRequestDTO pessoaDTO) {
        PessoaGetPersistDTO pessoaBanco = repository.getReferenceById(pessoaDTO.id());

        Pessoa pessoa = new Pessoa(pessoaBanco);

        pessoa.atualizarPessoa(pessoaDTO);

        PessoaGetPersistDTO pessoaAtualizada = new PessoaGetPersistDTO(pessoa);

        return new PessoaGetResponseDTO(repository.save(pessoaAtualizada));
    }
}

/*
 * changes:
 * - @Service acho que é dispensável no nosso projeto
 * - eu acho que você confundiu um pouco sobre aonde colocar a anotação @Autowired
 * - UseCases são camadas internas da aplicação, então ela tem acesso ao banco
 *     de dados por si própria, até mesmo pq senão eles não conseguiriam delegar
 *     o fluxo do programa. Ou seja, ele não recebe repository por parametro
 * - ok, é só exluir o construtor e colocar @Autowired em repository...
 *     N. @Autowired e final keyword não combinam juntas, ou seja, tira final
 * - você não precisa verificar a existencia de uma entidade no banco se
 *     o spring já oferece um método que faz isso... aka. getReferenceById()
 * - o resto do caminho é o seguinte: pega o DTO de banco, transforma em uma
 *     model. pega os dados do DTO de requisição e pede pro model se atualizar
 *     por fim, persiste a model no banco(passando pra DTO de banco novamente)
 *     e envia a resposta (o recurso atualizado) em forma de DTO de resposta.
 * - retornando uma String??
 * - @Valid e correlatos da jakarta.validation são fazem sentido aqui pois quem
 *     valida algo é a model, então, no máximo seria lá
 */
