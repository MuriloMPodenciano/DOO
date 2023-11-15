package ifsp.doo.atas.domain.controller;


import ifsp.doo.atas.domain.model.DadosAtualizacaoPessoa;
import ifsp.doo.atas.domain.model.DadosCadastroPessoa;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.model.PessoaRepository;
import ifsp.doo.atas.domain.usecases.pessoa.CadastrarPessoaUseCase;
import ifsp.doo.atas.domain.usecases.pessoa.EditarPessoaUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @PostMapping
    public String cadastrar(@RequestBody @Valid DadosCadastroPessoa dados) {
        CadastrarPessoaUseCase useCase = new CadastrarPessoaUseCase(repository);
        return useCase.cadastrarPessoa(dados);
    }

    @PutMapping
    @Transactional
    public String atualizar(@RequestBody @Valid DadosAtualizacaoPessoa dados) {
        EditarPessoaUseCase useCase = new EditarPessoaUseCase(repository);
        return useCase.editarPessoa(dados);
    }

    @PutMapping("/mudaStatus/{id}")
    public String atualizaStatus(@PathVariable Long id) {
        Optional<Pessoa> pessoaOptional = repository.findById(id);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            pessoa.mudarStatus();
            repository.save(pessoa);

            return "Status da pessoa alterado com sucesso.";
        } else {
            return "Pessoa não encontrada.";
        }
    }
}
