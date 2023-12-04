package ifsp.doo.atas.domain.usecases.ata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.informe.InformeGetResponseDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePutRequestDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.model.Informe;
import ifsp.doo.atas.domain.repository.AtaRepository;
import jakarta.persistence.EntityNotFoundException;

public class EditarInformeUseCase {
    @Autowired
    private AtaRepository repository;

    public InformeGetResponseDTO add(Long ataId, InformePostRequestDTO informe) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        ata.adicionarInforme(new Informe(informe));

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new InformeGetResponseDTO(
            repository.save(ataAtualizada).getInformes().get(ataBanco.getInformes().size())
        );
    }

    public InformeGetResponseDTO update(Long ataId, InformePutRequestDTO informeDTO) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        int index = findById(ata.getInformes(), informeDTO.id());

        ata.getInformes().get(index).atualizarInforme(informeDTO);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new InformeGetResponseDTO(repository.save(ataAtualizada).getInformes().get(index));
    }

    public InformeGetResponseDTO informe(Long ataId, Long informeId) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        int index = findById(ata.getInformes(), informeId);

        ata.getInformes().get(index).informar();

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new InformeGetResponseDTO(repository.save(ataAtualizada).getInformes().get(index));
    }

    public void remove(Long ataId, Long informeId) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        int index = findById(ata.getInformes(), informeId);

        ata.getInformes().remove(index);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        repository.save(ataAtualizada);
    }

    private int findById(List<Informe> informes, Long id) {
        for (int i = 0; i < informes.size(); i++) {
            if (informes.get(i).getId() == id)
                return i;
        }
        throw new EntityNotFoundException("cannot find Informe with id :" + id);
    }
}
