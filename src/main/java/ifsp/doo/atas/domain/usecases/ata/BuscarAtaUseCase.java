package ifsp.doo.atas.domain.usecases.ata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.repository.AtaRepository;

public class BuscarAtaUseCase {
    @Autowired
    private AtaRepository repository;

    public List<AtaGetResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    public List<AtaGetResponseDTO> getAllByPalavra(String palavra) {
        if (palavra == null)
            throw new IllegalArgumentException("palavra chave is empty in palavra chave mode");
    
        return repository.findAllByPalavraChave(palavra)
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    public List<AtaGetResponseDTO> getAllByDate(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (!isDateRangeCorrect(dataInicio, dataFim))
            throw new IllegalArgumentException("date period is invalid in date period mode");

        return repository.findAllByRange(dataInicio, dataFim)
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    public List<AtaGetResponseDTO> getAllByGrupo(Long id) {
        if (id == null)
            throw new IllegalArgumentException("grupo id is empty in grupo id mode");

        return repository.findAllByGrupoId(id)
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    private boolean isDateRangeCorrect(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return dataInicio != null && dataFim != null
            && dataInicio.compareTo(dataFim) <= 0;
    }

    public AtaGetResponseDTO get(Long id) {
        return new AtaGetResponseDTO(repository.getReferenceById(id));
    }

    public PDF toPDF(Long id) {

    }
}
