package ru.test;

import ru.test.cache.Cache;
import ru.test.data.Repository;

public class Storage<K, V> {
    private Repository<K, V> repository;
    private Cache<K, V> cache;

    public Storage(Repository<K, V> repository, Cache<K, V> cache) {
        this.repository = repository;
        this.cache = cache;
    }

    public V get(K key) {
        V value = getFromCache(key);
        if (value == null) {
            value = getFromRepository(key);
            cache.put(key, value);
        }

        return value;
    }

    public void clear() {
        cache.clear();
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
