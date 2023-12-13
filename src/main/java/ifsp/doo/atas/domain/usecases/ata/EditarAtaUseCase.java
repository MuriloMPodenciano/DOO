package ifsp.doo.atas.domain.usecases.ata;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPutRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.repository.AtaRepository;
import jakarta.persistence.EntityNotFoundException;

@Transactional
public class EditarAtaUseCase {
    @Autowired
    private AtaRepository repository;

    public PessoaGetResponseDTO addPessoa(Long ataId, PessoaGetResponseDTO pessoa) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        ata.marcarPresenca(new Pessoa(pessoa));

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new PessoaGetResponseDTO(findById(ataAtualizada.getListaPresenca(), pessoa.id()));
    }

    private PessoaGetPersistDTO findById(Set<PessoaGetPersistDTO> pessoas, Long id) {
        Iterator<PessoaGetPersistDTO> iterador = pessoas.iterator();

        while (iterador.hasNext()) {
            PessoaGetPersistDTO pessoa = iterador.next();
            if (pessoa.getId() == id)
                return pessoa;
        }
        throw new EntityNotFoundException("cannot find Informe with id :" + id);
    }

    public AtaGetResponseDTO update(AtaPutRequestDTO ataDTO) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataDTO.id());

        Ata ata = new Ata(ataBanco);

        ata.atualizarAta(ataDTO);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(repository.save(ataAtualizada));
    }

    public AtaGetResponseDTO begin(Long id) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(id);

        Ata ata = new Ata(ataBanco);

        ata.iniciarAta();

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(repository.save(ataAtualizada));
    }

    public AtaGetResponseDTO finish(Long id) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(id);

        Ata ata = new Ata(ataBanco);

        ata.finalizarAta();

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(repository.save(ataAtualizada));
    }
}
