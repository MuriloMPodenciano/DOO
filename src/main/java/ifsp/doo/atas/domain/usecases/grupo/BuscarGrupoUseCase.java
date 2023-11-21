package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class BuscarGrupoUseCase {
    @Autowired
    private GrupoRepository repository;

    public List<GrupoGetPersistDTO> getAll(){
        return repository.findAll();
    }

    public GrupoGetPersistDTO getById(@PathVariable Long id) {
        return repository.getById(id);
    }
}
