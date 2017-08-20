package ru.test.cache;

import ru.test.strategy.DisplacementStrategy;

import java.io.Serializable;

public class TwoLevelCache<K, V extends Serializable> implements Cache<K, V> {
    private Cache<K, V> level1;
    private Cache<K, V> level2;

    public TwoLevelCache(int maxCacheElementCountLevel1,
                         int maxCacheElementCountLevel2,
                         DisplacementStrategy<K, V> displacementStrategy,
                         String baseDirectoryPath) {
        this.level1 = new MemoryCache<>(maxCacheElementCountLevel1, displacementStrategy);
        this.level2 = new FileSystemCache<>(maxCacheElementCountLevel2, displacementStrategy,
                baseDirectoryPath);
    }

    @Override
    public V get(K key) {
        if (level1.contains(key)) {
            return level1.get(key);
        } else {
            if (level2.contains(key)) {
                V value = level2.get(key);
                level1.cache(key, value);
                return value;
            } else {
                return null;
            }
        }
    }

    @Override
    public void cache(K key, V value) {
        level1.cache(key, value);
        level2.cache(key, value);
    }

    @Override
    public void remove(K key) {
        level1.remove(key);
        level2.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return level1.contains(key) || level2.contains(key);
    }
}
