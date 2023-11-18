package ifsp.doo.atas.domain.usecases.ata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.model.AtaBuscarModo;
import ifsp.doo.atas.domain.repository.AtaRepository;

public class BuscarAtaUseCase {
    @Autowired
    private AtaRepository ataDAO;

    public List<AtaGetResponseDTO> getAll(AtaGetRequestDTO request) {
        if (request.modo() == AtaBuscarModo.TODOS)
            return ataDAO.findAll()
                .stream()
                .map(AtaGetResponseDTO::new)
                .collect(Collectors.toList());

        if (request.modo() == AtaBuscarModo.PALAVRA_CHAVE && request.palavraChave() == null)
            throw new IllegalArgumentException("palavra chave is empty in palavra chave mode");

        else if (
            request.modo() == AtaBuscarModo.DATE &&
            (
                (request.dataInicio() == null || request.dataFim() == null) ||
                request.dataInicio().compareTo(request.dataFim()) > 0
            )
        )
            throw new IllegalArgumentException("date period is invalid in date period mode");
        
        else if (request.modo() == AtaBuscarModo.GRUPO_ID && request.grupoId() == null)
            throw new IllegalArgumentException("grupo id is empty in grupo id mode");

        List<AtaGetPersistDTO> atas;

        switch (request.modo()) {
            case PALAVRA_CHAVE:
                atas = ataDAO.findAllByPalavraChave(request.palavraChave());
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
