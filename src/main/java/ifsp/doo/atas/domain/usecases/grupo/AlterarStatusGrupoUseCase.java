package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.GrupoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AlterarStatusGrupoUseCase {
    @Autowired
    private GrupoRepository repository;

    public Grupo alterarStatus(@Valid @NotNull Long id){
        Grupo grupo = repository.getReferenceById(id);
        grupo.mudarStatus();

        return grupo;
    }
}
