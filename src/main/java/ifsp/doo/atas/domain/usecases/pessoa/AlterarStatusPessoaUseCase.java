package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.model.PessoaRepository;
import ifsp.doo.atas.domain.persistence.PessoaDAO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Transactional
public class AlterarStatusPessoaUseCase {

    private PessoaRepository repository;

    public AlterarStatusPessoaUseCase(PessoaRepository repository) {
        this.repository = repository;
    }

    public String alterarStatus(@Valid @NotNull Long id){
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
