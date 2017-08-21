package ru.test.strategy;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.test.cache.CacheElement;

import java.util.HashMap;
import java.util.Map;

public class LeastRecentlyUsedStrategyTest {
    private DisplacementStrategy<Integer, String> strategy = new LeastRecentlyUsedStrategy<>();

    @Test
    public void emptyMapTest() {
        Integer result = strategy.getDisplacementElementKey(new HashMap<>());
        Assert.assertNull(result);
    }

    @Test
    public void differentLastUsageTest() throws InterruptedException {
        Map<Integer, CacheElement<String>> elements = new HashMap<>();
        CacheElement<String> firstValue = new CacheElement<>("First string");
        Thread.sleep(1);
        CacheElement<String> secondValue = new CacheElement<>("Second string");
        Thread.sleep(1);
        CacheElement<String> thirdValue = new CacheElement<>("Third string");

        elements.put(1, firstValue);
        elements.put(2, secondValue);
        elements.put(3, thirdValue);

        Integer result = strategy.getDisplacementElementKey(elements);
        Assert.assertEquals(result, new Integer(1));
    }

    @Test
    public void equalsLastUsageTest() {
        Map<Integer, CacheElement<String>> elements = new HashMap<>();
        CacheElement<String> firstValue = new CacheElement<>("First string");
        CacheElement<String> secondValue = new CacheElement<>("Second string");
        CacheElement<String> thirdValue = new CacheElement<>("Third string");

        elements.put(1, firstValue);
        elements.put(2, secondValue);
        elements.put(3, thirdValue);

        Integer result = strategy.getDisplacementElementKey(elements);
        Assert.assertNotNull(result);
    }

    @Test
    public void singleElementTest() {
        Map<Integer, CacheElement<String>> elements = new HashMap<>();
        CacheElement<String> firstValue = new CacheElement<>("First string");
        firstValue.registerRequest();
        elements.put(1, firstValue);

        Integer result = strategy.getDisplacementElementKey(elements);
        Assert.assertEquals(result, new Integer(1));
    }
}
