package ru.test.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.test.exception.InputParameterException;

public class LevelSizeValidatorTest {

    @DataProvider
    public Object[][] invalidSizes() {
        return new Object[][] {
                {-1, 30},
                {0, 20},
                {10, -1},
                {10, 0},
                {10, 10},
                {30, 10}
        };
    }

    @Test
    public void positiveTest() {
        boolean isExceptionCaught = false;
        try {
            new LevelSizeValidator(10, 30).validate();
        } catch (InputParameterException e) {
            isExceptionCaught = true;
        }

        Assert.assertFalse(isExceptionCaught);
    }

    @Test(dataProvider = "invalidSizes", expectedExceptions = InputParameterException.class)
    public void negativeTest(int sizeLevel1, int sizeLeve2) throws InputParameterException {
        new LevelSizeValidator(sizeLevel1, sizeLeve2).validate();
    }
}
