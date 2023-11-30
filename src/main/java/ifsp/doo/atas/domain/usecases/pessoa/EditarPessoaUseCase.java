package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPutRequestDTO;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EditarPessoaUseCase {
    @Autowired
    private PessoaRepository repository;

    public PessoaGetResponseDTO editarPessoa(PessoaPutRequestDTO pessoaDTO) {
        PessoaGetPersistDTO pessoaBanco = repository.getReferenceById(pessoaDTO.id());

        Pessoa pessoa = new Pessoa(pessoaBanco);

        pessoa.atualizarPessoa(pessoaDTO);

        PessoaGetPersistDTO pessoaAtualizada = new PessoaGetPersistDTO(pessoa);

        return new PessoaGetResponseDTO(repository.save(pessoaAtualizada));
    }
}
