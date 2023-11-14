package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.model.DadosCadastroGrupo;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.GrupoRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CadastrarGrupoUseCase {
    private GrupoRepository repository;

    public void cadastrarGrupo(@Valid DadosCadastroGrupo dados){
        if(repository.existByNome(dados.nome())){
            throw new DataIntegrityViolationException("Grupo com nome " + dados.nome() + " já existe.");
        }

        Grupo grupo = new Grupo(dados.nome(), dados.funcionarios());
        repository.save(grupo);

    }
}
