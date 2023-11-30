package ifsp.doo.atas.domain.usecases.ata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaPutRequestDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.model.Informe;
import ifsp.doo.atas.domain.model.Pauta;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.repository.AtaRepository;

@Transactional
public class EditarAtaUseCase {
    @Autowired
    private AtaRepository repository;

    public AtaGetResponseDTO updateAta(AtaPutRequestDTO ataDTO) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataDTO.id());

        Ata ata = new Ata(ataBanco);

        ata.atualizarAta(ataDTO);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(repository.save(ataAtualizada));
    }

    public AtaGetResponseDTO addPessoa(Long ataId, PessoaGetResponseDTO pessoa) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        ata.marcarPresenca(new Pessoa(pessoa));

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(repository.save(ataAtualizada));
    }

    public AtaGetResponseDTO addPauta(Long id, PautaPostRequestDTO pauta) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(id);

        Ata ata = new Ata(ataBanco);

        ata.adicionarPauta(new Pauta(pauta));

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(repository.save(ataAtualizada));
    }

    public AtaGetResponseDTO addInforme(Long id, InformePostRequestDTO informe) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(id);

        Ata ata = new Ata(ataBanco);

        ata.adicionarInforme(new Informe(informe));

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new AtaGetResponseDTO(repository.save(ataAtualizada));
    }
}
