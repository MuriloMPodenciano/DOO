package ifsp.doo.atas.domain.usecases.ata;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.PreAtaPostRequestDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.repository.AtaDAO;

public class CadastrarAtaUseCase {
    @Autowired
    private AtaDAO ataDAO;

    public AtaGetResponseDTO createPreAta(PreAtaPostRequestDTO preAtaDTO) {
        Ata ata = new Ata(preAtaDTO);

        AtaGetPersistDTO persistedAta = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(ataDAO.save(persistedAta));
    }

    public AtaGetResponseDTO reCreate(AtaPostRequestDTO ataDTO) {
        Ata ata = new Ata(ataDTO);

        AtaGetPersistDTO persistedAta = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(ataDAO.save(persistedAta));
    }
}
