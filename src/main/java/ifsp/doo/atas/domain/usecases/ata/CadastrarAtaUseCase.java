package ifsp.doo.atas.domain.usecases.ata;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.ata.PreAtaPostRequestDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.repository.AtaRepository;
import ifsp.doo.atas.domain.repository.GrupoRepository;

public class CadastrarAtaUseCase {
    @Autowired
    private AtaRepository ataDAO;

    @Autowired
    private GrupoRepository grupoRepository;

    public AtaGetResponseDTO createPreAta(PreAtaPostRequestDTO preAtaDTO) {
        if (!grupoRepository.existsById(preAtaDTO.grupo().id()))
            throw new IllegalArgumentException("Grupo not found: " + preAtaDTO.grupo().id());

        Ata ata = new Ata(preAtaDTO);

        AtaGetPersistDTO persistedAta = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(ataDAO.save(persistedAta));
    }
}
