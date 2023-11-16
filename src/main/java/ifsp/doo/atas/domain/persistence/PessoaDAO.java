package ifsp.doo.atas.domain.persistence;

import ifsp.doo.atas.domain.model.Pessoa;
import ifsp.doo.atas.domain.model.PessoaRepository;

import java.util.List;
import java.util.Optional;

public class PessoaDAO implements DAO<Long, Pessoa>{

    private final PessoaRepository repository;

    public PessoaDAO(PessoaRepository repository) {
        this.repository = repository;
    }

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

    public boolean existsByName(String nome){
        return repository.existsByName(nome);
    }

    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }
}
