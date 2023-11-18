package ifsp.doo.atas.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.doo.atas.domain.DTO.ata.AtaGetRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPutRequestDTO;
import ifsp.doo.atas.domain.DTO.ata.PreAtaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.usecases.ata.BuscarAtaUseCase;
import ifsp.doo.atas.domain.usecases.ata.CadastrarAtaUseCase;
import ifsp.doo.atas.domain.usecases.ata.EditarAtaUseCase;

@RestController
@RequestMapping("/ata")
public class AtaController {
    @Autowired
    private BuscarAtaUseCase buscarAta;

    @Autowired
    private CadastrarAtaUseCase cadastrarAta;

    @Autowired
    private EditarAtaUseCase editarAta;

    @GetMapping
    public List<AtaGetResponseDTO> getAllAtas(@RequestBody AtaGetRequestDTO request) {
        return buscarAta.getAll(request);
    }

    @PostMapping
    public AtaGetResponseDTO createPreAta(@RequestBody PreAtaPostRequestDTO preAtaDTO) {
        return cadastrarAta.createPreAta(preAtaDTO);
    }

    @PostMapping("/{id}/listaPresenca")
    public AtaGetResponseDTO addPessoa(@PathVariable("id") Long id, @RequestBody PessoaGetResponseDTO pessoa) {
        return editarAta.addPessoa(id, pessoa);
    }

    @PostMapping("/{id}/pautas")
    public AtaGetResponseDTO addPautas(@PathVariable("id") Long id, @RequestBody PautaPostRequestDTO pauta) {
        return editarAta.addPauta(id, pauta);
    }

    @PostMapping("/{id}/informes")
    public AtaGetResponseDTO addPinformes(@PathVariable("id") Long id, @RequestBody InformePostRequestDTO informe) {
        return editarAta.addInforme(id, informe);
    }

    @PutMapping
    public AtaGetResponseDTO updateAta(@RequestBody AtaPutRequestDTO ataDTO) {
        return editarAta.updateAta(ataDTO);
    }
}
