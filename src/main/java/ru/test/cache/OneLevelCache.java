package ru.test.cache;

import ru.test.strategy.DisplacementStrategy;

public abstract class OneLevelCache<K, V> implements Cache<K, V> {
    final DisplacementStrategy<K, V> strategy;
    final int maxCacheElementCount;

    public OneLevelCache(int maxCacheElementCount, DisplacementStrategy strategy) {
        this.maxCacheElementCount = maxCacheElementCount;
        this.strategy = strategy;
    }

    public abstract V get(K key);

    public abstract boolean contains(K key);

    public abstract int size();
}
