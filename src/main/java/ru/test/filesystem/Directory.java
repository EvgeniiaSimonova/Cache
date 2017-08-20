package ru.test.filesystem;

import java.io.File;
import java.io.Serializable;

public class Directory<K, V extends Serializable> {

    private final File baseDirectory;

    public Directory(String baseDirectoryPath) {
        this.baseDirectory = createBaseDirectory(baseDirectoryPath);
    }

    public void createFile(K key, V value) {
        File file = new File(getFilePath(key));
        ObjectConverter.serialize(file, value);
    }

    public V getValue(K key) {
        File file = new File(getFilePath(key));
        return ObjectConverter.deserialize(file);
    }

    public void deleteFile(K key) {
        File file = new File(getFilePath(key));
        file.delete();
    }

    private File createBaseDirectory(String baseDirectoryPath) {
        File baseDirectory = new File(baseDirectoryPath);
        if (!baseDirectory.exists()) {
            baseDirectory.mkdir();
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
