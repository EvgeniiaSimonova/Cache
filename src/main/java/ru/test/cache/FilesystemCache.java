package ru.test.cache;

import ru.test.exception.DeserializableException;
import ru.test.exception.InputParameterException;
import ru.test.exception.SerializableException;
import ru.test.filesystem.Directory;
import ru.test.strategy.DisplacementStrategy;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.logging.Logger;

public class FilesystemCache<K, V extends Serializable> extends OneLevelCache<K, V> {

    private final Logger logger = Logger.getLogger(FileInputStream.class.getName());
    private final Directory<K, V> directory;

    public FilesystemCache(int maxCacheElementCount, DisplacementStrategy<K, V> strategy, String baseDirectoryPath)
            throws InputParameterException {
        super(maxCacheElementCount, strategy);
        this.directory = new Directory<>(baseDirectoryPath);
    }

    @Override
    public V get(K key) {
        V value = null;
        if (contains(key)) {
            try {
                value = directory.getValue(key);
                CacheElement<V> cacheElement = elements.get(key);
                cacheElement.registerRequest();
            } catch (DeserializableException e) {
                logger.severe(e.getMessage());
                remove(key);
            }
        }

        return value;
    }

    @Override
    public void put(K key, V value) {
        if (size() >= maxCacheElementCount) {
            K displacementElementKey = strategy.getDisplacementElementKey(elements);
            elements.remove(displacementElementKey);
            directory.deleteFile(displacementElementKey);
        }
        try {
            directory.createFile(key, value);
            elements.put(key, new CacheElement<>(null));
        } catch (SerializableException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    public void remove(K key) {
        elements.remove(key);
        directory.deleteFile(key);
    }

    @Override
    public void clear() {
        elements.clear();
        directory.clear();
    }
}
