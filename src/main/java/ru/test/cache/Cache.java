package ru.test.cache;

public interface Cache<K, V> {
    V get(K key);
    void cache(K key, V value);
    void remove(K key);
    boolean contains(K key);
}
