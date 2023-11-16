package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.GrupoRepository;
import ifsp.doo.atas.domain.persistence.GrupoDAO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AlterarStatusGrupoUseCase {
    @Autowired
    private GrupoRepository repository;
    private GrupoDAO dao;

    public AlterarStatusGrupoUseCase(GrupoRepository repository) {
        this.dao = new GrupoDAO(repository);
    }

    public String alterarStatus(@Valid @NotNull Long id){
        if(!dao.get(id).isPresent()){
            throw new EntityNotFoundException("Grupo " + id + " nao existe.");
        }

        dao.changeStatus(id);
        return "Status do grupo alterado com sucesso";
    }
}
