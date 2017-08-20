package ru.test;

import ru.test.cache.Cache;

public class Storage<K, V> {
    private DataRepository<K, V> repository;
    private Cache<K, V> cache;

    public V get(K key) {
        V value = getFromCache(key);
        if (value == null) {
            value = getFromRepository(key);
            cache.cache(key, value);
        }

        return value;
    }

    // may be it is better
    /*public V get2(K key) {
        V value;
        if (cache.contains(key)) {
            value = cache.get(key);
        } else {
            value = repository.get(key);
            cache.cache(key, value);
        }

        return value;
    }*/

    private V getFromCache(K key) {
        return cache.get(key);
    }

    private V getFromRepository(K key) {
        return repository.get(key);
    }
}
