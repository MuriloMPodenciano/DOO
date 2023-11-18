package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPostRequestDTO;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CadastrarGrupoUseCase {
    @Autowired
    private GrupoRepository repository;

    public GrupoGetResponseDTO cadastrarGrupo(GrupoPostRequestDTO grupoDTO) {
        if (repository.existsByNome(grupoDTO.nome()))
            throw new DataIntegrityViolationException("Grupo already exists: " + grupoDTO.nome());

        Grupo grupo = new Grupo(grupoDTO);

        GrupoGetPersistDTO novoGrupo = new GrupoGetPersistDTO(grupo);

        return new GrupoGetResponseDTO(repository.save(novoGrupo));
    }
}

/*
 * changes:
 * - UseCases são camadas internas da aplicação, então ela tem acesso ao banco
 *     de dados por si própria, até mesmo pq senão eles não conseguiriam delegar
 *     o fluxo do programa. Ou seja, ele não recebe repository por parametro
 * - removi o DAO
 * - pra que passar cada atributo de grupoDTO para grupo se vc pode passar o DTO todo
 * - o caminho é o seguinte: verifica se existe um grupo de mesmo nome, pega os dados
 *     do DTO de requisição e transforma em uma model para que ela valide os dados,
 *     então persiste a model no banco(passando pra DTO de banco) e envia a resposta
 *     (o recurso criado) em forma de DTO de resposta.
 * - retornando uma String??
 * - @Valid e correlatos da jakarta.validation são fazem sentido aqui pois quem
 *     valida algo é a model, então, no máximo seria lá
 */
