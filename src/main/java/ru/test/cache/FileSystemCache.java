package ru.test.cache;

import ru.test.filesystem.Directory;
import ru.test.strategy.DisplacementStrategy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FileSystemCache<K, V extends Serializable> extends OneLevelCache<K, V> {

    private final Map<K, CacheElement<V>> elements;
    private final Directory<K, V> directory;

    public FileSystemCache(int maxCacheElementCount, DisplacementStrategy<K, V> strategy, String baseFolderPath) {
        super(maxCacheElementCount, strategy);
        this.elements = new HashMap<>();
        this.directory = new Directory<>(baseFolderPath);
    }

    @Override
    public V get(K key) {
        if (contains(key)) {
            CacheElement<V> cacheElement = elements.get(key);
            cacheElement.registerRequest();
            return directory.getValue(key);
        } else {
            return null;
        }
    }

    @Override
    public void cache(K key, V value) {
        if (size() >= maxCacheElementCount) {
            K displacementElementKey = strategy.getDisplacementElementKey(elements);
            elements.remove(displacementElementKey);
            directory.deleteFile(displacementElementKey);
        }
        elements.put(key, new CacheElement<>(null));
        directory.createFile(key, value);
    }

    @Override
    public void remove(K key) {
        elements.remove(key);
        directory.deleteFile(key);
    }

    @Override
    public boolean contains(K key) {
        return elements.containsKey(key);
    }

    @Override
    public int size() {
        return elements.size();
    }
}
