package ru.test.strategy;

import ru.test.cache.CacheElement;

import java.util.Map;
import java.util.Optional;

// Вытеснение давно неиспользуемых
public class LeastRecentlyUsedStrategy<K, V> implements DisplacementStrategy<K, V> {
    @Override
    public K getDisplacementElementKey(Map<K, CacheElement<V>> elements) {
        Optional<K> optional = elements.keySet().stream().min((o1, o2) -> {
            CacheElement<V> cacheElement1 = elements.get(o1);
            CacheElement<V> cacheElement2 = elements.get(o2);
            return Long.compare(cacheElement1.getLastUsedTime(), cacheElement2.getLastUsedTime());
        });

        return optional.orElse(null);
    }
}