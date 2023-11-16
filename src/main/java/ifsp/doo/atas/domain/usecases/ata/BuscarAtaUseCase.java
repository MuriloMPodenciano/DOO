package ifsp.doo.atas.domain.usecases.ata;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.model.AtaBuscarMode;
import ifsp.doo.atas.domain.repository.AtaDAO;

public class BuscarAtaUseCase {
    @Autowired
    private AtaDAO ataDAO;

    public List<AtaGetResponseDTO> getAll(AtaGetRequestDTO request) {
        if (request.modo() == AtaBuscarMode.ID && request.id() == null)
            throw new IllegalArgumentException("id is empty in id mode");

        if (
            request.modo() == AtaBuscarMode.DATE &&
            (
                (request.dataInicio() == null || request.dataFim() == null) ||
                request.dataInicio().compareTo(request.dataFim()) > 0
            )
        )
            throw new IllegalArgumentException("id is empty in id mode");
        
        if (request.modo() == AtaBuscarMode.GRUPO_ID && request.grupoId() == null)
            throw new IllegalArgumentException("id is empty in id mode");

        List<AtaGetPersistDTO> atas;
        
        switch (request.modo()) {
            case ID:
                Optional<AtaGetPersistDTO> ata = ataDAO.findById(request.id());

                if (ata.isPresent()) 
                    atas = Arrays.asList(ata.get());
                else
                    atas = Arrays.asList();
                break;
            case DATE:
                atas = ataDAO.findAllByRange(request.dataInicio(), request.dataFim());
                break;
            case GRUPO_ID:
                atas = ataDAO.findAllByGrupoId(request.grupoId());
                break;
            default:
                atas = Arrays.asList();
        }

        return atas
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }
}
