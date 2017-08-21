package ru.test.filesystem;

import ru.test.exception.DeserializableException;
import ru.test.exception.SerializableException;

import java.io.*;

public class ObjectConverter {
    public static <V extends Serializable> void serialize(File file, V value) throws SerializableException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
            objectOutputStream.writeObject(value);
        } catch (IOException e) {
            throw new SerializableException("Can not write Object to file. Reason: " + e.getMessage());
        }
    }

    public static <V extends Serializable> V deserialize(File file) throws DeserializableException {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            return (V) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DeserializableException("Can not read Object from file. Reason: " + e.getMessage());
        }
    }
}
