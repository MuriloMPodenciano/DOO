package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.model.DadosCadastroPessoa;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.model.PessoaRepository;
import ifsp.doo.atas.domain.persistence.PessoaDAO;
import ifsp.doo.atas.domain.utils.Notification;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@Transactional
public class CadastrarPessoaUseCase {

    @Autowired
    private PessoaRepository repository;
    private PessoaDAO dao;

    public CadastrarPessoaUseCase(PessoaRepository repository) {
        this.dao = new PessoaDAO(repository);
    }

    public String cadastrarPessoa(@Valid DadosCadastroPessoa dados) {

        if (dao.existsByName(dados.nome())) {
            throw new DataIntegrityViolationException("Já existe uma pessoa cadastrada com esse nome: " + dados.nome());
        }

        Pessoa novaPessoa = new Pessoa(dados.nome(), dados.email(), dados.cargo());
        repository.save(novaPessoa);

        return "Pessoa cadastrada com sucesso.";
    }
}
