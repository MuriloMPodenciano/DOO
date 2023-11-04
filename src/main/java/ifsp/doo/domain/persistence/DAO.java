package ifsp.doo.domain.persistence;

import java.util.List;
import java.util.Optional;

public interface DAO<K, V> {
    List<V> getAll();

    Optional<V> get(K key);

    V create(V value);

    V update(K key, V value);

    boolean remove(V value);
    
    boolean removeByKey(K key);
}
