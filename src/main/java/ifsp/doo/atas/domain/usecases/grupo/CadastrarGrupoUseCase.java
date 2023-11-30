package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPostRequestDTO;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CadastrarGrupoUseCase {
    @Autowired
    private GrupoRepository repository;

    public GrupoGetResponseDTO cadastrarGrupo(GrupoPostRequestDTO grupoDTO) {
        if (repository.existsByNome(grupoDTO.nome()))
            throw new DuplicateKeyException("Grupo already exists: " + grupoDTO.nome());

        Grupo grupo = new Grupo(grupoDTO);

        GrupoGetPersistDTO novoGrupo = new GrupoGetPersistDTO(grupo);

        return new GrupoGetResponseDTO(repository.save(novoGrupo));
    }
}
