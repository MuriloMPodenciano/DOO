package ifsp.doo.atas.domain.persistence;

import ifsp.doo.atas.domain.model.Grupo;
import ifsp.doo.atas.domain.model.GrupoRepository;

import java.util.List;
import java.util.Optional;

public class GrupoDAO implements DAO<Long, Grupo>{
    private GrupoRepository repository;

    public GrupoDAO(GrupoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Grupo> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Grupo> get(Long id) {
        return repository.findById(id);
    }

    public boolean existsByName(String nome){
        return repository.existByNome(nome);
    }

    @Override
    public Grupo create(Grupo grupo) {
        return repository.save(grupo);
    }

    @Override
    public Grupo update(Long id, Grupo grupo) {
        return repository.save(grupo);
    }

    public boolean changeStatus(Long id){
        Grupo grupo = repository.getReferenceById(id);
        grupo.mudarStatus();
        return true;
    }

    @Override
    public boolean remove(Grupo grupo) {
        repository.delete(grupo);
        return true;
    }

    @Override
    public boolean removeByKey(Long id) {
        return false;
    }
}
