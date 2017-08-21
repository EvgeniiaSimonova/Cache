package ru.test.strategy;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.test.cache.CacheElement;

import java.util.HashMap;
import java.util.Map;

public class LeastFrequentlyUsedStrategyTest {
    private DisplacementStrategy<Integer, String> strategy = new LeastFrequentlyUsedStrategy<>();

    @Test
    public void emptyMapTest() {
        Integer result = strategy.getDisplacementElementKey(new HashMap<>());
        Assert.assertNull(result);
    }

    @Test
    public void differentFrequencyTest() {
        Map<Integer, CacheElement<String>> elements = new HashMap<>();
        CacheElement<String> firstValue = new CacheElement<>("First string");
        firstValue.registerRequest();
        CacheElement<String> secondValue = new CacheElement<>("Second string");
        CacheElement<String> thirdValue = new CacheElement<>("Third string");
        thirdValue.registerRequest();
        thirdValue.registerRequest();

        elements.put(1, firstValue);
        elements.put(2, secondValue);
        elements.put(3, thirdValue);

        Integer result = strategy.getDisplacementElementKey(elements);
        Assert.assertEquals(result, new Integer(2));
    }

    @Test
    public void equalsFrequencyTest() {
        Map<Integer, CacheElement<String>> elements = new HashMap<>();
        CacheElement<String> firstValue = new CacheElement<>("First string");
        firstValue.registerRequest();
        CacheElement<String> secondValue = new CacheElement<>("Second string");
        secondValue.registerRequest();
        CacheElement<String> thirdValue = new CacheElement<>("Third string");
        thirdValue.registerRequest();

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
