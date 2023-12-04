package ifsp.doo.atas.domain.usecases.ata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPutRequestDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.model.Pauta;
import ifsp.doo.atas.domain.repository.AtaRepository;
import jakarta.persistence.EntityNotFoundException;

public class EditarPautaUseCase {
    @Autowired
    private AtaRepository repository;

    public PautaGetResponseDTO add(Long ataId, PautaPostRequestDTO pauta) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        ata.adicionarPauta(new Pauta(pauta));

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new PautaGetResponseDTO(
            repository.save(ataAtualizada).getPautas().get(ataBanco.getPautas().size())
        );
    }

    public PautaGetResponseDTO update(Long ataId, PautaPutRequestDTO pautaDTO) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        int index = findById(ata.getPautas(), pautaDTO.id());

        ata.getPautas().get(index).atualizarPauta(pautaDTO);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new PautaGetResponseDTO(repository.save(ataAtualizada).getPautas().get(index));
    }

    public PautaGetResponseDTO discuss(Long ataId, Long pautaId) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        int index = findById(ata.getPautas(), pautaId);

        ata.getPautas().get(index).colocarEmPauta();

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new PautaGetResponseDTO(repository.save(ataAtualizada).getPautas().get(index));
    }

    public PautaGetResponseDTO base(Long ataId, Long pautaId) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        int index = findById(ata.getPautas(), pautaId);

        ata.getPautas().get(index).pautar();

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new PautaGetResponseDTO(repository.save(ataAtualizada).getPautas().get(index));
    }

    public void remove(Long ataId, Long pautaId) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        int index = findById(ata.getPautas(), pautaId);

        ata.getPautas().remove(index);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        repository.save(ataAtualizada);
    }

    private int findById(List<Pauta> pautas, Long id) {
        for (int i = 0; i < pautas.size(); i++) {
            if (pautas.get(i).getId() == id)
                return i;
        }
        throw new EntityNotFoundException("cannot find Pauta with id :" + id);
    }
}
