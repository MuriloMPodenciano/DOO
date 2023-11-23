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

    @PutMapping("/{id}")
    public PessoaGetResponseDTO atualizar(@RequestBody PessoaPutRequestDTO pessoaDTO) {
        return editarPesssoa.editarPessoa(pessoaDTO);
    }
}
