package ifsp.doo.atas.domain.persistence;

import ifsp.doo.atas.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class GrupoDAO implements DAO<Long, Grupo>{
    private JpaRepository<Grupo, Long> repository;

    public GrupoDAO(JpaRepository<Grupo, Long> repository) {
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

    @Override
    public Grupo create(Grupo grupo) {
        return repository.save(grupo);
    }

    @Override
    public Grupo update(Long id, Grupo grupo) {
        return repository.save(grupo);
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
