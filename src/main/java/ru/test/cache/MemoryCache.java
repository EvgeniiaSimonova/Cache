package ru.test.cache;

import ru.test.strategy.DisplacementStrategy;

public class MemoryCache<K, V> extends OneLevelCache<K, V> {

    public MemoryCache(int maxCacheElementCount, DisplacementStrategy<K, V> strategy) {
        super(maxCacheElementCount, strategy);
    }

    @Override
    public V get(K key) {
        if (contains(key)) {
            CacheElement<V> cacheElement = elements.get(key);
            cacheElement.registerRequest();
            return cacheElement.getValue();
        } else {
            return null;
        }
    }

    @Override
    public void put(K key, V value) {
        if (size() >= maxCacheElementCount) {
            K displacementElementKey = strategy.getDisplacementElementKey(elements);
            elements.remove(displacementElementKey);
        }
        elements.put(key, new CacheElement<>(value));
    }

    @Override
    public void remove(K key) {
        elements.remove(key);
    }

    @Override
    public void clear() {
        elements.clear();
    }
}
