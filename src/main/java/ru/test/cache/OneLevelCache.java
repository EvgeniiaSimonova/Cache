package ru.test.cache;

import ru.test.strategy.DisplacementStrategy;

import java.util.HashMap;
import java.util.Map;

public abstract class OneLevelCache<K, V> implements Cache<K, V> {
    final Map<K, CacheElement<V>> elements;
    final DisplacementStrategy<K, V> strategy;
    final int maxCacheElementCount;

    public OneLevelCache(int maxCacheElementCount, DisplacementStrategy<K, V> strategy) {
        this.maxCacheElementCount = maxCacheElementCount;
        this.strategy = strategy;
        this.elements = new HashMap<>();
    }

    public abstract V get(K key);

    public boolean contains(K key) {
        return elements.containsKey(key);
    }

    public int size() {
        return elements.size();
    }
}
