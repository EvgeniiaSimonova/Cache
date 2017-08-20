package ru.test.strategy;

import ru.test.cache.CacheElement;

import java.util.Map;

public interface DisplacementStrategy<K, V> {
    K getDisplacementElementKey(Map<K, CacheElement<V>> elements);
}
