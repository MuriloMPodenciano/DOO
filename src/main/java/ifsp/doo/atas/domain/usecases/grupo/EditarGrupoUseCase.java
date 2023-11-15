package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.model.DadosAtualizacaoGrupo;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.GrupoRepository;
import ifsp.doo.atas.domain.persistence.GrupoDAO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EditarGrupoUseCase {
    @Autowired
    private GrupoRepository repository;
    private GrupoDAO dao;

    public EditarGrupoUseCase(GrupoRepository repository) {
        this.dao = new GrupoDAO(repository);
    }

    public String editarGrupo(@Valid DadosAtualizacaoGrupo dados){
        if(!dao.get(dados.id()).isPresent()){
            throw new EntityNotFoundException("Grupo " + dados.id() + " nao existe.");
        }
        Grupo grupo = new Grupo(dados.id(), dados.nome(), dados.funcionarios());
        dao.update(dados.id(), grupo);
        return "Grupo editado com sucesso.";
    }
}
