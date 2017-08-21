package ru.test.util;

import ru.test.exception.InputParameterException;

public class LevelSizeValidator {
    private int sizeLevel1;
    private int sizeLevel2;

    public LevelSizeValidator(int sizeLevel1, int sizeLevel2) {
        this.sizeLevel1 = sizeLevel1;
        this.sizeLevel2 = sizeLevel2;
    }

    public void validate() throws InputParameterException {
        if (sizeLevel1 <= 0) {
            throw new InputParameterException("The size of Level 1 must be positive");
        }

        if (sizeLevel2 <= 0) {
            throw new InputParameterException("The size of Level 2 must be positive");
        }

        if (sizeLevel1 >= sizeLevel2) {
            throw new InputParameterException("The size of Level 1 must be less than Level 2 size");
        }
    }
}
