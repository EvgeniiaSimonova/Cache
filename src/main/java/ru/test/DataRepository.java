package ru.test;

import java.util.HashMap;
import java.util.Map;

public class DataRepository<K, V> {
    private Map<K, V> data;

    public DataRepository() {
        this.data = new HashMap<>();
    }

    public DataRepository(Map<K, V> data) {
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
