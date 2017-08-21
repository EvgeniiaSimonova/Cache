package ru.test.filesystem;

import ru.test.exception.DeserializableException;
import ru.test.exception.InputParameterException;
import ru.test.exception.SerializableException;

import java.io.File;
import java.io.Serializable;

public class Directory<K, V extends Serializable> {

    private final File baseDirectory;

    public Directory(String baseDirectoryPath) throws InputParameterException {
        this.baseDirectory = createBaseDirectory(baseDirectoryPath);
    }

    public void createFile(K key, V value) throws SerializableException {
        File file = new File(getFilePath(key));
        ObjectConverter.serialize(file, value);
    }

    public V getValue(K key) throws DeserializableException {
        File file = new File(getFilePath(key));
        return ObjectConverter.deserialize(file);
    }

    public void deleteFile(K key) {
        File file = new File(getFilePath(key));
        file.delete();
    }

    public void clear() {
        File[] files = baseDirectory.listFiles();
        if (files != null) {
            for (File file: files) {
                file.delete();
            }
        }
        baseDirectory.delete();
    }

    private File createBaseDirectory(String baseDirectoryPath) throws InputParameterException {
        File baseDirectory = new File(baseDirectoryPath);
        if (!baseDirectory.exists()) {
            boolean isCreated = baseDirectory.mkdir();
            if (!isCreated) {
                throw new InputParameterException(
                        String.format("Can not create folder in path '%S'", baseDirectoryPath));
            }
        }

        return baseDirectory;
    }

    private String getFilePath(Object key) {
        return new StringBuilder(baseDirectory.getAbsolutePath())
                .append("\\")
                .append(key)
                .toString();
    }
}
