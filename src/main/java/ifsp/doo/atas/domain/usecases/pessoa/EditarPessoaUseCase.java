package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.model.DadosAtualizacaoPessoa;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.model.PessoaRepository;
import ifsp.doo.atas.domain.persistence.PessoaDAO;
import ifsp.doo.atas.domain.utils.Notification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class EditarPessoaUseCase {

    @Autowired
    private PessoaRepository repository;
    private PessoaDAO dao;

    public EditarPessoaUseCase(PessoaRepository repository) {
        this.dao = new PessoaDAO(repository);
    }

    @Autowired
    public String editarPessoa(@Valid DadosAtualizacaoPessoa dados) {
        Pessoa pessoa = dao.get(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa " + dados.id() + " não existe."));

        pessoa.atualizarPessoa(pessoa);
        dao.update(dados.id(), pessoa);

        return "Pessoa editada com sucesso.";
    }
}
