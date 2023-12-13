package ifsp.doo.atas.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.doo.atas.domain.DTO.pauta.PautaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPutRequestDTO;
import ifsp.doo.atas.domain.usecases.ata.EditarPautaUseCase;

@RestController
@RequestMapping("/ata/{id}/pautas")
public class PautaController {
    @Autowired
    private EditarPautaUseCase editarPauta;

    @PostMapping
    public PautaGetResponseDTO add(@PathVariable("id") Long ataId, @RequestBody PautaPostRequestDTO pauta) {
        return editarPauta.add(ataId, pauta);
    }

    @PutMapping("/{pautaId}")
    public PautaGetResponseDTO update(@PathVariable("id") Long ataId, @RequestBody PautaPutRequestDTO pautaDTO) {
        return editarPauta.update(ataId, pautaDTO);
    }

    @DeleteMapping("/{pautaId}")
    public void remove(@PathVariable("id") Long ataId, @PathVariable("pautaId") Long pautaId) {
        editarPauta.remove(ataId, pautaId);
    }

    @DeleteMapping("/Unbased/{pautaId}")
    public PautaGetResponseDTO discuss(@PathVariable("id") Long ataId, @PathVariable("pautaId") Long pautaId) {
        return editarPauta.discuss(ataId, pautaId);
    }

    @DeleteMapping("/on-discuss/{pautaId}")
    public PautaGetResponseDTO base(@PathVariable("id") Long ataId, @PathVariable("pautaId") Long pautaId) {
        return editarPauta.base(ataId, pautaId);
    }
}
