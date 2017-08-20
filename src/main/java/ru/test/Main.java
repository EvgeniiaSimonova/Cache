package ru.test;

import ru.test.cache.OneLevelCache;
import ru.test.cache.CacheElement;
import ru.test.strategy.DisplacementStrategy;
import ru.test.strategy.LeastFrequentlyUsedStrategy;
import ru.test.strategy.LeastRecentlyUsedStrategy;
import ru.test.strategy.MostRecentlyUsedStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Main {
    OneLevelCache oneLevelCache;
    static DisplacementStrategy<String, String> strategy;

    public static void main(String[] args) throws InterruptedException {
        /*Map<String, String> elements = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            elements.put(UUID.randomUUID().toString(), String.format("String %d", i));
        }*/

        Map<String, CacheElement<String>> elements = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String uuid = UUID.randomUUID().toString();
            CacheElement<String> element = new CacheElement<>(String.format("String %d", i));
            System.out.println(uuid + " : " + element.getValue() + "\t" + element.getLastUsedTime());
            elements.put(uuid, element);
            Thread.sleep(1);
        }

        int i = 0;
        for (String key: elements.keySet()) {
            elements.get(key).registerRequest();
            Thread.sleep(1);
            i++;
            if (i == 99) break;
        }
        System.out.println("-----------------------------");
        for (String key: elements.keySet()) {
            System.out.println(key + " : " + elements.get(key).getValue() + "\t" + elements.get(key).getUsesCount());
        }

        strategy = new LeastRecentlyUsedStrategy<>();
        String key = strategy.getDisplacementElementKey(elements);
        System.out.println("Key: " + key);
        System.out.println("Value: " + elements.get(key).getValue());

        strategy = new MostRecentlyUsedStrategy<>();
        key = strategy.getDisplacementElementKey(elements);
        System.out.println("Key: " + key);
        System.out.println("Value: " + elements.get(key).getValue());

        strategy = new LeastFrequentlyUsedStrategy<>();
        key = strategy.getDisplacementElementKey(elements);
        System.out.println("Key: " + key);
        System.out.println("Value: " + elements.get(key).getValue());
    }

    private static String getRandomKey(String[] keys) {
        int randomIndex = new Random().nextInt(keys.length);
        return keys[randomIndex];
    }
}
