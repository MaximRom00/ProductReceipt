package by.romanchuk.service;

import java.util.List;

public interface ServiceInterface<K, T> {

    void saveOrUpdate(T t);

    void delete(K k);

    T getById(K k, K k1);

    List<T> getAllProducts();
}
