package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.model.DadosAtualizacaoPessoa;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.model.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EditarPessoaUseCase {

    private final PessoaRepository repository;

    @Autowired
    public EditarPessoaUseCase(PessoaRepository repository) {
        this.repository = repository;
    }

    public String editarPessoa(@Valid DadosAtualizacaoPessoa dados) {
        Pessoa pessoa = repository.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa " + dados.id() + " não existe."));

        pessoa.atualizarPessoa(pessoa);
        repository.save(pessoa);

        return "Pessoa editada com sucesso.";
    }
}
