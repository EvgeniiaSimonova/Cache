package ru.test.strategy;

import ru.test.cache.CacheElement;

import java.util.Map;
import java.util.Optional;

//Вытеснение наименее часто используемых
public class LeastFrequentlyUsedStrategy<K, V> implements DisplacementStrategy<K, V> {
    @Override
    public K getDisplacementElementKey(Map<K, CacheElement<V>> elements) {
        Optional<K> optional = elements.keySet().stream().min((o1, o2) -> {
            CacheElement<V> cacheElement1 = elements.get(o1);
            CacheElement<V> cacheElement2 = elements.get(o2);
            return Integer.compare(cacheElement1.getUsesCount(), cacheElement2.getUsesCount());
        });

        return optional.orElse(null);
    }
}
