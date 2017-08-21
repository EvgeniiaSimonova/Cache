package ru.test.strategy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.test.exception.InputParameterException;

public class DisplacementStrategyFactoryTest {

    private DisplacementStrategyFactory factory = new DisplacementStrategyFactory();

    @DataProvider
    public Object[][] validStrategies() {
        return new Object[][] {
                {"LFU", LeastFrequentlyUsedStrategy.class},
                {"LRU", LeastRecentlyUsedStrategy.class},
                {"MRU", MostRecentlyUsedStrategy.class}
        };
    }

    @Test(dataProvider = "validStrategies")
    public void getStrategyByNameTest(String name, Class clazz) throws InputParameterException {
        DisplacementStrategy strategy = factory.createStrategy(name);
        Assert.assertTrue(strategy.getClass().equals(clazz));
    }

    @Test(expectedExceptions = InputParameterException.class)
    public void invalidStrategyNameTest() throws InputParameterException {
        factory.createStrategy("invalid");
    }
}
