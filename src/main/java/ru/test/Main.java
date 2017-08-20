package ru.test;

import ru.test.cache.Cache;
import ru.test.cache.TwoLevelCache;
import ru.test.data.MemoryRepository;
import ru.test.data.Repository;
import ru.test.exception.InputParameterException;
import ru.test.strategy.DisplacementStrategyFactory;

import java.util.*;

public class Main {

    private final static int ELEMENTS_COUNT = 100;
    private final static int ITERATION_COUNT = 1000;
    private final static List<String> keys = new ArrayList<>(ELEMENTS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Maximum count of cache elements on level 1 is ");
        int sizeLevel1 = scanner.nextInt();

        System.out.print("Maximum count of cache elements on level 2 is ");
        int sizeLevel2 = scanner.nextInt();

        System.out.print("Available displacement strategies: LFU, LRU, MRU. Chosen ");
        String strategyName = scanner.next();

        System.out.print("Base directory for the second cache level is ");
        String baseDirectoryPath = scanner.next();

        try {
            Repository<String, String> repository = getRepository();
            Cache<String, String> cache = getCache(sizeLevel1, sizeLevel2, strategyName, baseDirectoryPath);
            Storage<String, String> storage = new Storage<>(repository, cache);

            for (int i = 0; i < ITERATION_COUNT; i++) {
                storage.get(getRandomKey());
                Thread.sleep(1);
            }

            storage.clear();
        } catch (InputParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Repository<String, String> getRepository() {
        Repository<String, String> repository = new MemoryRepository<>();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            String key = UUID.randomUUID().toString();
            keys.add(key);
            repository.add(key, String.format("String number %d", i));
        }

        return repository;
    }

    private static Cache<String, String> getCache(int sizeLevel1,
                                                  int sizeLevel2,
                                                  String strategyName,
                                                  String baseDirectoryPath) throws InputParameterException {
        DisplacementStrategyFactory<String, String> factory = new DisplacementStrategyFactory<>();
        return new TwoLevelCache<>(sizeLevel1,
                sizeLevel2, factory.createStrategy(strategyName), baseDirectoryPath);

    }

    private static String getRandomKey() {
        return keys.get(new Random().nextInt(ELEMENTS_COUNT));
    }
}
