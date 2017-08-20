package ru.test.data;

public interface Repository<K, V> {
    void add(K key, V value);
    V get(K key);
}
