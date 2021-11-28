package by.romanchuk.dao;

import java.util.List;
import java.util.Optional;

public interface DaoInterface<K, T> {
    void saveOrUpdate(T t);

    void delete(K k);

    T getById(K k, K k1);

    List<T> getAllProducts();
}
