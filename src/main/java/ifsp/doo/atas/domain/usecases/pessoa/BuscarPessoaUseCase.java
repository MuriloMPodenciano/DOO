package ifsp.doo.atas.domain.usecases.pessoa;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class BuscarPessoaUseCase {
    @Autowired
    private PessoaRepository repository;
    public List<PessoaGetPersistDTO> getAll(){
        return repository.findAll();
    }
    public PessoaGetPersistDTO getById(@PathVariable Long id) {
        return repository.getReferenceById(id);
    }
}
