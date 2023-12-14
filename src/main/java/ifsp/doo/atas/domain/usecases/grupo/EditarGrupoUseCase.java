package ifsp.doo.atas.domain.usecases.grupo;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPutRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.repository.GrupoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class EditarGrupoUseCase {
    @Autowired
    private GrupoRepository repository;

    public GrupoGetResponseDTO editarGrupo(GrupoPutRequestDTO grupoDTO) {
        GrupoGetPersistDTO grupoBanco = repository.getReferenceById(grupoDTO.id());

        Grupo grupo = new Grupo(grupoBanco);

        grupo.atualizarGrupo(grupoDTO);

        GrupoGetPersistDTO grupoAtualizado = new GrupoGetPersistDTO(grupo);

        return new GrupoGetResponseDTO(repository.save(grupoAtualizado));
    }

    public GrupoGetResponseDTO addFuncionario(Long id, PessoaGetResponseDTO funcionario) {
        GrupoGetPersistDTO grupoBanco = repository.getReferenceById(id);

        Grupo grupo = new Grupo(grupoBanco);

        grupo.inserirFuncionario(new Pessoa(funcionario));

        GrupoGetPersistDTO grupoAtualizado = new GrupoGetPersistDTO(grupo);

        return new GrupoGetResponseDTO(repository.save(grupoAtualizado));
    }

    public GrupoGetResponseDTO addFuncionarios(Long id, List<PessoaGetPersistDTO> funcionarios){
        GrupoGetPersistDTO grupoBanco = repository.getReferenceById(id);

        Grupo grupo = new Grupo(grupoBanco);

        for(PessoaGetPersistDTO funcionario : funcionarios){
            grupo.inserirFuncionario(new Pessoa(funcionario));
        }

        GrupoGetPersistDTO grupoAtualizado = new GrupoGetPersistDTO(grupo);

        return new GrupoGetResponseDTO(repository.save(grupoAtualizado));
    }
}
