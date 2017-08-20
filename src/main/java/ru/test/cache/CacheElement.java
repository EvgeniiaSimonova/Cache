package ru.test.cache;

import java.util.Date;

public class CacheElement<V> {
    private final V value;
    private final long occurrenceTime;
    private long lastUsedTime;
    private int usesCount;

    public CacheElement(V value) {
        this.value = value;
        this.occurrenceTime = new Date().getTime();
        this.lastUsedTime = this.occurrenceTime;
        this.usesCount = 1;
    }

    public void registerRequest() {
        lastUsedTime = new Date().getTime();
        usesCount++;
    }

    public V getValue() {
        return value;
    }

    public long getOccurrenceTime() {
        return occurrenceTime;
    }

    public long getLastUsedTime() {
        return lastUsedTime;
    }

    public int getUsesCount() {
        return usesCount;
    }
}
