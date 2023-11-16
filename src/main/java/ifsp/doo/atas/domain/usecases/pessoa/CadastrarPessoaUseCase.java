package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.model.DadosCadastroPessoa;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.model.PessoaRepository;
import ifsp.doo.atas.domain.utils.Notification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CadastrarPessoaUseCase {

    private final PessoaRepository repository;

    @Autowired
    public CadastrarPessoaUseCase(PessoaRepository repository) {
        this.repository = repository;
    }

    public String cadastrarPessoa(@Valid DadosCadastroPessoa dados) {
        if (repository.existsByName(dados.nome())) {
            throw new DataIntegrityViolationException("Já existe uma pessoa cadastrada com esse nome: " + dados.nome());
        }

        Pessoa novaPessoa = new Pessoa(dados.nome(), dados.email(), dados.cargo());
        repository.save(novaPessoa);

        return "Pessoa cadastrada com sucesso.";
    }
}
