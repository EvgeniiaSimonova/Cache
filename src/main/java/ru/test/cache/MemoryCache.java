package ru.test.cache;

import ru.test.strategy.DisplacementStrategy;

import java.util.HashMap;
import java.util.Map;

public class MemoryCache<K, V> extends OneLevelCache<K, V> {

    private Map<K, CacheElement<V>> elements;

    public MemoryCache(int maxCacheElementCount, DisplacementStrategy strategy) {
        super(maxCacheElementCount, strategy);
        this.elements = new HashMap<>();
    }

    @Override
    public V get(K key) { // may be replace by contains?
        if (contains(key)) {
            CacheElement<V> cacheElement = elements.get(key);
            cacheElement.registerRequest();
            return cacheElement.getValue();
        } else {
            return null;
        }
    }

    @Override
    public void cache(K key, V value) {
        if (size() >= maxCacheElementCount) { // may be replace by == ?
            K displacementElementKey = strategy.getDisplacementElementKey(elements);
            elements.remove(displacementElementKey);
        }
        elements.put(key, new CacheElement<>(value));
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
