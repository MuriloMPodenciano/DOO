package ifsp.doo.atas.domain.usecases.ata;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPutRequestDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.repository.AtaDAO;

public class EditarAtaUseCase {
    @Autowired
    private AtaDAO ataDAO;

    public AtaGetResponseDTO updateAta(AtaPutRequestDTO ataDTO) {
        AtaGetPersistDTO ataBanco = ataDAO.getReferenceById(ataDTO.id());

        Ata ata = new Ata(ataBanco);

        ata.atualizarAta(ataDTO);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(ataDAO.save(ataAtualizada));
    }
}
