package ru.test.cache;

import ru.test.exception.InputParameterException;

import java.io.Serializable;
import java.util.logging.Logger;

public class TwoLevelCache<K, V extends Serializable> implements Cache<K, V> {
    private final Logger logger = Logger.getLogger(TwoLevelCache.class.getName());
    private Cache<K, V> level1;
    private Cache<K, V> level2;

    public TwoLevelCache(Cache<K, V> level1, Cache<K, V> level2) throws InputParameterException {
        this.level1 = level1;
        this.level2 = level2;
    }

    @Override
    public V get(K key) {
        V value = level1.get(key);
        if (value != null) {
            logger.info("Value: " + value + " was found in L1");
        } else {
            value = level2.get(key);
            if (value != null) {
                logger.info("Value: " + value + " was found in L2");
                level1.put(key, value);
            }
        }

        return value;
    }

    @Override
    public void put(K key, V value) {
        level1.put(key, value);
        level2.put(key, value);
    }

    @Override
    public void remove(K key) {
        level1.remove(key);
        level2.remove(key);
    }

    @Override
    public void clear() {
        level1.clear();
        level2.clear();
    }

    @Override
    public boolean contains(K key) {
        return level1.contains(key) || level2.contains(key);
    }
}
