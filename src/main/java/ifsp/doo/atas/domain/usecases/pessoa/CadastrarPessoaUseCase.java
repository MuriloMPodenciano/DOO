package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPostRequestDTO;
import ifsp.doo.atas.domain.model.Email;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CadastrarPessoaUseCase {
    @Autowired
    private PessoaRepository repository;

    public PessoaGetResponseDTO cadastrarPessoa(PessoaPostRequestDTO pessoaDTO) {
        if (repository.existsByEmail(new Email(pessoaDTO.email())))
            throw new DataIntegrityViolationException("Email already exists: " + pessoaDTO.email());

        Pessoa pessoa = new Pessoa(pessoaDTO);

        PessoaGetPersistDTO novaPessoa = new PessoaGetPersistDTO(pessoa);

        return new PessoaGetResponseDTO(repository.save(novaPessoa));
    }
}
