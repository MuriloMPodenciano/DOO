package ifsp.doo.atas.domain.usecases.ata;

import java.util.ArrayList;
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
    private AtaRepository repository;

    public List<AtaGetResponseDTO> getAll(AtaGetRequestDTO request) {
        List<AtaGetPersistDTO> atas = switch (request.modo()) {
            case PALAVRA_CHAVE -> {
                if (request.modo() == AtaBuscarModo.PALAVRA_CHAVE && request.palavraChave() == null)
                    throw new IllegalArgumentException("palavra chave is empty in palavra chave mode");

                yield repository.findAllByPalavraChave(request.palavraChave());
            }
            case DATE -> {
                if (request.modo() == AtaBuscarModo.DATE && !isDateRangeCorrect(request))
                    throw new IllegalArgumentException("date period is invalid in date period mode");

                yield repository.findAllByRange(request.dataInicio(), request.dataFim());
            }
            case GRUPO_ID -> {
                if (request.modo() == AtaBuscarModo.GRUPO_ID && request.grupoId() == null)
                    throw new IllegalArgumentException("grupo id is empty in grupo id mode");

                yield repository.findAllByGrupoId(request.grupoId());
            }
            default -> repository.findAll();
        };

        return atas
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    private boolean isDateRangeCorrect(AtaGetRequestDTO request) {
        return request.dataInicio() != null &&
            request.dataFim() != null &&
            request.dataInicio().compareTo(request.dataFim()) <= 0;
    }

    public AtaGetResponseDTO get(Long id) {
        return new AtaGetResponseDTO(repository.getReferenceById(id));
    }

    public PDF toPDF(Long id) {

    }
}
