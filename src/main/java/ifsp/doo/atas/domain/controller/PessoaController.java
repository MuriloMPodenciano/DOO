package ifsp.doo.atas.domain.controller;


import ifsp.doo.atas.domain.model.DadosAtualizacaoPessoa;
import ifsp.doo.atas.domain.model.DadosCadastroPessoa;
import ifsp.doo.atas.domain.model.PessoaRepository;
import ifsp.doo.atas.domain.persistence.PessoaDAO;
import ifsp.doo.atas.domain.usecases.pessoa.AlterarStatusPessoaUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.CadastrarPessoaUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.EditarPessoaUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @PostMapping
    public String cadastrar(@RequestBody @Valid DadosCadastroPessoa dados) {
        CadastrarPessoaUseCase useCase = new CadastrarPessoaUseCase(new PessoaDAO(repository));
        return useCase.cadastrarPessoa(dados);
    }

    @PutMapping
    @Transactional
    public String atualizar(@RequestBody @Valid DadosAtualizacaoPessoa dados) {
        EditarPessoaUseCase useCase = new EditarPessoaUseCase(new PessoaDAO(repository));
        return useCase.editarPessoa(dados);
    }

    @PutMapping
    @RequestMapping("/mudaStatus/{id}")
    public String atualizaStatus(@PathVariable Long id) {
        AlterarStatusPessoaUseCase useCase = new AlterarStatusPessoaUseCase(new PessoaDAO(repository));
        return useCase.alterarStatus(id);
    }
}
