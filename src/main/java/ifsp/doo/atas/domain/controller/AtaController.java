package ifsp.doo.atas.domain.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPutRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.PreAtaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.usecases.ata.BuscarAtaUseCase;
import ifsp.doo.atas.domain.usecases.ata.CadastrarAtaUseCase;
import ifsp.doo.atas.domain.usecases.ata.EditarAtaUseCase;

@RestController
@RequestMapping("/ata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AtaController {
    @Autowired
    private BuscarAtaUseCase buscarAta;

    @Autowired
    private CadastrarAtaUseCase cadastrarAta;

    @Autowired
    private EditarAtaUseCase editarAta;

    @GetMapping
    public List<AtaGetResponseDTO> getAllAtas() {
        return buscarAta.getAll();
    }

    @GetMapping("/{id}")
    public AtaGetResponseDTO get(@PathVariable("id") Long id) {
        return buscarAta.get(id);
    }

    @GetMapping("/byPalavra/{palavra}")
    public List<AtaGetResponseDTO> getAllAtasByPalavra(@PathVariable("palavra") String palavra) {
        return buscarAta.getAllByPalavra(palavra);
    }

    @GetMapping("/byDate/{initialDate}/{finalDate}")
    public List<AtaGetResponseDTO> getAllAtasByDate(@PathVariable("initialDate") LocalDateTime dataInicio, @PathVariable("finalDate") LocalDateTime dataFim) {
        return buscarAta.getAllByDate(dataInicio, dataFim);
    }

    @GetMapping("/byGrupo/{id}")
    public List<AtaGetResponseDTO> getAllAtasByGrupo(@PathVariable("id") Long id) {
        return buscarAta.getAllByGrupo(id);
    }

    @PostMapping
    public AtaGetResponseDTO createPreAta(@RequestBody PreAtaPostRequestDTO preAtaDTO) {
        return cadastrarAta.createPreAta(preAtaDTO);
    }

    @PostMapping("/{id}/listaPresenca")
    public PessoaGetResponseDTO addPessoa(@PathVariable("id") Long id, @RequestBody PessoaGetResponseDTO pessoa) {
        return editarAta.addPessoa(id, pessoa);
    }

    @PutMapping("/{id}")
    public AtaGetResponseDTO update(@RequestBody AtaPutRequestDTO ataDTO) {
        return editarAta.update(ataDTO);
    }

    @DeleteMapping("/pre-ata/{id}")
    public AtaGetResponseDTO begin(@PathVariable("id") Long id) {
        return editarAta.begin(id);
    }

    @DeleteMapping("/iniciated-ata/{id}")
    public AtaGetResponseDTO finish(@PathVariable("id") Long id) {
        return editarAta.finish(id);
    }
}
