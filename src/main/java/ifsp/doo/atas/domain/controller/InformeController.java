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

import ifsp.doo.atas.domain.DTO.informe.InformeGetResponseDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePutRequestDTO;
import ifsp.doo.atas.domain.usecases.ata.EditarInformeUseCase;

@RestController
@RequestMapping("/ata/{id}/informes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InformeController {
    @Autowired
    private EditarInformeUseCase editarInforme;

    @PostMapping
    public InformeGetResponseDTO add(@PathVariable("id") Long ataId, @RequestBody InformePostRequestDTO informe) {
        return editarInforme.add(ataId, informe);
    }

    @PutMapping("/{informeId}")
    public InformeGetResponseDTO update(@PathVariable("id") Long ataId, @RequestBody InformePutRequestDTO informeDTO) {
        return editarInforme.update(ataId, informeDTO);
    }

    @DeleteMapping("/{informeId}")
    public void remove(@PathVariable("id") Long ataId, @PathVariable("informeId") Long informeId) {
        editarInforme.remove(ataId, informeId);
    }

    @DeleteMapping("/Uninformed/{informeId}")
    public InformeGetResponseDTO informe(@PathVariable("id") Long ataId, @PathVariable("informeId") Long informeId) {
        return editarInforme.informe(ataId, informeId);
    }
}
