package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.model.DadosAtualizacaoGrupo;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.GrupoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

public class EditarGrupoUseCase {
    @Autowired
    private GrupoRepository repository;

    public void editarGrupo(@Valid DadosAtualizacaoGrupo dados){
        Grupo grupo = repository.getReferenceById(dados.id());
        grupo.atualizarGrupo(dados);
    }
}
