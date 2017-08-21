package ru.test.filesystem;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.test.exception.DeserializableException;
import ru.test.exception.InputParameterException;
import ru.test.exception.SerializableException;

public class DirectoryTest {
    private Directory<String, String> directory;

    @BeforeClass
    public void beforeClass() throws InputParameterException {
        directory = new Directory<>("testDir");
    }

    @Test
    public void getExistsFileTest() throws SerializableException, DeserializableException {
        String fileName = "file1";
        String content = "It is content of the file";
        directory.createFile(fileName, content);
        String actualContent = directory.getValue(fileName);
        directory.deleteFile(fileName);

        Assert.assertEquals(actualContent, content);
    }

    @Test(expectedExceptions = DeserializableException.class)
    public void getNonExistsFileTest() throws SerializableException, DeserializableException {
        String fileName = "file2";
        String content = "It is content of the file";
        directory.createFile(fileName, content);
        directory.deleteFile(fileName);

        directory.getValue(fileName);
    }

    @AfterClass
    public void afterClass() {
        directory.clear();
    }
}
