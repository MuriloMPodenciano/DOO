package ifsp.doo.atas.domain.controller;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPutRequestDTO;
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

    @PostMapping
    public GrupoGetResponseDTO cadastrar(@RequestBody GrupoPostRequestDTO dados) {
        return cadastrarGrupo.cadastrarGrupo(dados);
    }

    @PutMapping("/{id}")
    public GrupoGetResponseDTO atualizar(@RequestBody GrupoPutRequestDTO dados) {
        return editarGrupo.editarGrupo(dados);
    }

    @GetMapping
    public List<GrupoGetPersistDTO> getAll(){
        return buscarGrupo.getAll();
    }

    @GetMapping("/{id}")
    public GrupoGetPersistDTO getById(@PathVariable Long id){
        return buscarGrupo.getById(id);
    }
}

/*
 * changes:
 * - o controller n deve ter acesso direto ao banco
 * - com o spring, tal como o nosso projeto, nós n instanciamos nada
 *     usamos @Autowired para ter uma injeção de dependencia
 * - o estilo da nossa API será: o usuário sempre manda as novas versões dos recursos
 *     e de quais partes do recurso
 * - status é uma parte do recurso grupo, e é mudado, logo ele recebe o novo valor tbm.
 *     até então, receber o mesmo valor não será consederado um erro
 * - não iremos usar @Valid e afins do jakarta.validation. pelo menos não no controller/DTO
 * - como Grupo não uma entidade, tornar atualizar @Transactional não surte mais efeito
 * - retornar uma String??
 * - não sei dizer se está errado @RequestMapping em um método, mas os mapeadores de verbos
 *     já possuem um argumento de end-point path, então por via das duvidas, deixa só neles
 */
