package ifsp.doo.atas.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifsp.doo.atas.domain.DTO.discussao.DiscussaoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.discussao.DiscussaoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.discussao.DiscussaoPutRequestDTO;
import ifsp.doo.atas.domain.usecases.ata.EditarDiscussaoUseCase;

@RestController
@RequestMapping("/ata/{id}/pautas/{pautaId}/discussoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DiscussaoController {
    @Autowired
    private EditarDiscussaoUseCase editarDiscussao;

    @PostMapping
    public DiscussaoGetResponseDTO addDiscussao(@PathVariable("id") Long ataId, @PathVariable("pautaId") Long pautaId, @RequestBody DiscussaoPostRequestDTO discussao) {
        return editarDiscussao.add(ataId, pautaId, discussao);
    }

    @PutMapping("/{discussaoId}")
    public DiscussaoGetResponseDTO updateDiscussao(@PathVariable("id") Long ataId, @PathVariable("pautaId") Long pautaId, @RequestBody DiscussaoPutRequestDTO discussaoDTO) {
        return editarDiscussao.update(ataId, pautaId, discussaoDTO);
    }

    @DeleteMapping("/{discussaoId}")
    public void removeDiscussao(@PathVariable("id") Long ataId, @PathVariable("pautaId") Long pautaId, @PathVariable("discussaoId") Long discussaoId) {
        editarDiscussao.remove(ataId, pautaId, discussaoId);
    }
}
