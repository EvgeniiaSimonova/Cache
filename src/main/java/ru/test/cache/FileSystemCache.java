package ru.test.cache;

import ru.test.strategy.DisplacementStrategy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FileSystemCache<K, V extends Serializable> extends OneLevelCache<K, V> {

    private final String baseFolderPath;
    private final Map<K, CacheElement<String>> elementFiles;

    public FileSystemCache(int maxCacheElementCount, DisplacementStrategy strategy, String baseFolderPath) {
        super(maxCacheElementCount, strategy);
        this.baseFolderPath = baseFolderPath;
        this.elementFiles = new HashMap<>();
    }

    @Override
    public V get(K key) {
        if (contains(key)) {
            // find file in path and unserialize it
            return null;
        } else {
            return null;
        }
    }

    @Override
    public void cache(K key, V value) {
        
    }

    @Override
    public boolean contains(K key) {
        return elementFiles.containsKey(key);
    }

    @Override
    public int size() {
        return elementFiles.size();
    }
}
