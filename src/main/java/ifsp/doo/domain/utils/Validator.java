package ifsp.doo.domain.utils;

import java.util.Collection;

public abstract class Validator<V> {
    public abstract Notification validate(V value);

    public static boolean nullOrEmpty(String string) {
        return string == null || string.isBlank();
    }

    public static <T> boolean nullOrEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }
}
