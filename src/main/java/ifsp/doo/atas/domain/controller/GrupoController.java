package ifsp.doo.atas.domain.controller;

import ifsp.doo.atas.domain.model.DadosAtualizacaoGrupo;
import ifsp.doo.atas.domain.model.DadosCadastroGrupo;
import ifsp.doo.atas.domain.model.GrupoRepository;
import ifsp.doo.atas.domain.usecases.grupo.AlterarStatusGrupoUseCase;
import ifsp.doo.atas.domain.usecases.grupo.CadastrarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.grupo.EditarGrupoUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
    @Autowired
    private GrupoRepository repository;

    @PostMapping
    public String cadastrar(@RequestBody @Valid DadosCadastroGrupo dados){
        CadastrarGrupoUseCase useCase = new CadastrarGrupoUseCase(repository);
        return useCase.cadastrarGrupo(dados);
    }

    @PutMapping
    @Transactional
    public String atualizar(@RequestBody @Valid DadosAtualizacaoGrupo dados){
        EditarGrupoUseCase useCase = new EditarGrupoUseCase(repository);
        return useCase.editarGrupo(dados);
    }

    @PutMapping
    @RequestMapping("/mudaStatus/{id}")
    public String atualizaStatus(@PathVariable Long id){
        AlterarStatusGrupoUseCase useCase = new AlterarStatusGrupoUseCase(repository);
        return useCase.alterarStatus(id);
    }
}
