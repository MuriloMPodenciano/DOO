package ifsp.doo.atas.domain.usecases.ata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.discussao.DiscussaoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.discussao.DiscussaoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.discussao.DiscussaoPutRequestDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.model.Discussao;
import ifsp.doo.atas.domain.model.Pauta;
import ifsp.doo.atas.domain.repository.AtaRepository;
import jakarta.persistence.EntityNotFoundException;

public class EditarDiscussaoUseCase {
    @Autowired
    private AtaRepository repository;

    public DiscussaoGetResponseDTO add(Long id, Long pautaId, DiscussaoPostRequestDTO discussao) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(id);

        Ata ata = new Ata(ataBanco);

        int indexPauta = findPautaById(ata.getPautas(), pautaId);

        int indexDiscussao = ata.getPautas().get(indexPauta).getDiscussoes().size();

        ata.getPautas().get(indexPauta).addDiscussao(new Discussao(discussao));

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new DiscussaoGetResponseDTO(
            repository.save(ataAtualizada)
                .getPautas().get(indexPauta)
                .getDiscussoes().get(indexDiscussao)
        );
    }

    private int findPautaById(List<Pauta> pautas, Long id) {
        for (int i = 0; i < pautas.size(); i++) {
            if (pautas.get(i).getId() == id)
                return i;
        }
        throw new EntityNotFoundException("cannot find Pauta with id: " + id);
    }

    public DiscussaoGetResponseDTO update(Long id, Long pautaId, DiscussaoPutRequestDTO discussaoDTO) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(id);

        Ata ata = new Ata(ataBanco);

        int indexPauta = findPautaById(ata.getPautas(), pautaId);

        int indexDiscussao = findDiscussaoById(
            ata.getPautas().get(indexPauta).getDiscussoes(),
            discussaoDTO.id()
        );

        ata.getPautas().get(indexPauta).getDiscussoes().get(indexDiscussao).updateDiscussao(discussaoDTO);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        return new DiscussaoGetResponseDTO(
            repository.save(ataAtualizada)
                .getPautas().get(indexPauta)
                .getDiscussoes().get(indexDiscussao)
        );
    }

    private int findDiscussaoById(List<Discussao> discussoes, Long id) {
        for (int i = 0; i < discussoes.size(); i++) {
            if (discussoes.get(i).getId() == id)
                return i;
        }
        throw new EntityNotFoundException("cannot find Pauta with id: " + id);
    }

    public void remove(Long ataId, Long pautaId, Long discussaoId) {
        AtaGetPersistDTO ataBanco = repository.getReferenceById(ataId);

        Ata ata = new Ata(ataBanco);

        int indexPauta = findPautaById(ata.getPautas(), pautaId);

        Discussao discussao = findDiscussao(ata.getPautas().get(indexPauta).getDiscussoes(), discussaoId);

        ata.getPautas().get(indexPauta).removeDiscussao(discussao);

        AtaGetPersistDTO ataAtualizada = new AtaGetPersistDTO(ata);

        repository.save(ataAtualizada);
    }

    private Discussao findDiscussao(List<Discussao> discussoes, Long id) {
        for (Discussao discussao : discussoes) {
            if (discussao.getId() == id)
                return discussao;
        }
        throw new EntityNotFoundException("cannot find Pauta with id: " + id);
    }
}
