package ru.test.strategy;

import ru.test.exception.InputParameterException;

public class DisplacementStrategyFactory<K, V> {
    public DisplacementStrategy<K, V> createStrategy(String name) throws InputParameterException {
        DisplacementStrategy<K, V> strategy = null;

        switch (name) {
            case "LFU":
                strategy = new LeastFrequentlyUsedStrategy<>();
                break;
            case "LRU":
                strategy = new LeastRecentlyUsedStrategy<>();
                break;
            case "MRU":
                strategy = new MostRecentlyUsedStrategy<>();
                break;
            default:
                throw new InputParameterException(String.format("Can not find DisplacementStrategy by name '%s'", name));
        }

        return strategy;
    }
}
