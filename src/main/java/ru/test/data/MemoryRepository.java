package ru.test.data;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MemoryRepository<K, V> implements Repository<K, V> {
    private final Logger logger = Logger.getLogger(MemoryRepository.class.getName());
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
        V value = data.get(key);
        logger.info("Value: " + value + " was found in Repository");
        return value;
    }
}
