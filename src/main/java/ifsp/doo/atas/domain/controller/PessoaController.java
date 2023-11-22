package ifsp.doo.atas.domain.controller;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPutRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPostRequestDTO;
import ifsp.doo.atas.domain.usecases.pessoa.BuscarPessoaUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.CadastrarPessoaUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.EditarPessoaUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private CadastrarPessoaUseCase cadastrarPessoa;

    @Autowired
    private EditarPessoaUseCase editarPesssoa;

    @Autowired
    private BuscarPessoaUseCase buscarPessoa;

    @GetMapping
    public List<PessoaGetPersistDTO> getAll(){
        return buscarPessoa.getAll();
    }

    @GetMapping("/{id}")
    public PessoaGetPersistDTO getById(@PathVariable Long id){
        return buscarPessoa.getById(id);
    }

    @PostMapping
    public PessoaGetResponseDTO cadastrar(@RequestBody PessoaPostRequestDTO pessoaDTO) {
        return cadastrarPessoa.cadastrarPessoa(pessoaDTO);
    }

    @PostMapping("/{id}/lista")
    public PessoaGetResponseDTO cadastrarFuncionario(@RequestBody PessoaPostRequestDTO pessoaDTO) {
        return cadastrarPessoa.cadastrarPessoa(pessoaDTO);
    }

    @PutMapping("/{id}")
    public PessoaGetResponseDTO atualizar(@RequestBody PessoaPutRequestDTO pessoaDTO) {
        return editarPesssoa.editarPessoa(pessoaDTO);
    }


}

/*
 * changes:
 * - o controller n deve ter acesso direto ao banco
 * - com o spring, tal como o nosso projeto, nós n instanciamos nada
 *     usamos @Autowired para ter uma injeção de dependencia
 * - o estilo da nossa API será: o usuário sempre manda as novas versões dos recursos
 *     e de quais partes do recurso
 * - status é uma parte do recurso pessoa, e é mudado, logo ele recebe o novo valor tbm.
 *     até então, receber o mesmo valor não será consederado um erro
 * - não iremos usar @Valid e afins do jakarta.validation. pelo menos não no controller/DTO
 * - como Pessoa não uma entidade, tornar atualizar @Transactional não surte mais efeito
 * - retornar uma String??
 */