package ru.test.data;

import java.util.HashMap;
import java.util.Map;

public class MemoryRepository<K, V> implements Repository<K, V> {
    private Map<K, V> data;

    public MemoryRepository() {
        this.data = new HashMap<>();
    }

    public MemoryRepository(Map<K, V> data) {
        this.data = data;
    }

    public void add(K key, V value) {
        data.put(key, value);
    }

    public V get(K key) {
        return data.get(key);
    }

    public Map<K, V> getData() {
        return data;
    }

    public void setData(Map<K, V> data) {
        this.data = data;
    }
}
