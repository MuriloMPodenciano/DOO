package ifsp.doo.atas.domain.controller;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPutRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.usecases.grupo.BuscarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.grupo.CadastrarGrupoUseCase;
import ifsp.doo.atas.domain.usecases.grupo.EditarGrupoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
    @Autowired
    private CadastrarGrupoUseCase cadastrarGrupo;

    @Autowired
    private EditarGrupoUseCase editarGrupo;

    @Autowired
    private BuscarGrupoUseCase buscarGrupo;

    @GetMapping
    public List<GrupoGetPersistDTO> getAll(){
        return buscarGrupo.getAll();
    }

    @GetMapping("/{id}")
    public GrupoGetPersistDTO getById(@PathVariable Long id){
        return buscarGrupo.getById(id);
    }

    @PostMapping
    public GrupoGetResponseDTO cadastrar(@RequestBody GrupoPostRequestDTO dados) {
        return cadastrarGrupo.cadastrarGrupo(dados);
    }

    @PostMapping("/{id}/lista")
    public GrupoGetResponseDTO addFuncionario(@PathVariable("id") Long id, @RequestBody PessoaGetResponseDTO funcionario) {
        return editarGrupo.addFuncionario(id, funcionario);
    }

    @PutMapping("/{id}")
    public GrupoGetResponseDTO atualizar(@RequestBody GrupoPutRequestDTO dados) {
        return editarGrupo.editarGrupo(dados);
    }
}
