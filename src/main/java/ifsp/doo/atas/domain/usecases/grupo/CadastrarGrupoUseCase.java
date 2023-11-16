package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.model.DadosCadastroGrupo;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.GrupoRepository;
import ifsp.doo.atas.domain.persistence.GrupoDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CadastrarGrupoUseCase {
    @Autowired
    private GrupoRepository repository;
    private GrupoDAO dao;

    public CadastrarGrupoUseCase(GrupoRepository repository) {
        this.repository = repository;
        this.dao = new GrupoDAO(repository);
    }

    public String cadastrarGrupo(@Valid DadosCadastroGrupo dados){
        if(dao.existsByName(dados.nome())){
            throw new DataIntegrityViolationException("Grupo com nome " + dados.nome() + " já existe.");
        }
        Grupo grupo = new Grupo(dados.nome(), dados.funcionarios());
        dao.create(grupo);

        return "Grupo criado com sucesso.";
    }
}
