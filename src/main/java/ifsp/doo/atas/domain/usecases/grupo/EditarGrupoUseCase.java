package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.model.DadosAtualizacaoGrupo;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.GrupoRepository;
import jakarta.validation.Valid;

public class EditarGrupoUseCase {
    private GrupoRepository repository;

    public void editarGrupo(@Valid DadosAtualizacaoGrupo dados){
        Grupo grupo = repository.getReferenceById(dados.id());
        grupo.atualizarGrupo(dados);
    }
}
