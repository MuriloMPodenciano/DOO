package ifsp.doo.atas.domain.persistence;

import ifsp.doo.atas.domain.model.Pessoa;

import java.util.List;
import java.util.Optional;

public class PessoaDAO implements DAO<Long, Pessoa>{

    @Override
    public List<Pessoa> getAll() {
        return null;
    }

    @Override
    public Optional<Pessoa> get(Long key) {
        return Optional.empty();
    }

    @Override
    public Pessoa create(Pessoa value) {
        return null;
    }

    @Override
    public Pessoa update(Long key, Pessoa value) {
        return null;
    }

    @Override
    public boolean remove(Pessoa value) {
        return false;
    }

    @Override
    public boolean removeByKey(Long key) {
        return false;
    }
}
