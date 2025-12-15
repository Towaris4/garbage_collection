package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> softReferenceValue = new SoftReference<>(value);
        cache.put(key, softReferenceValue);
    }

    public final V get(K key) {
        SoftReference<V> ref = cache.get(key);
        if (ref != null) {
            V cachedValue = ref.get();
            if (cachedValue != null) {
                return cachedValue;
            }
        }
        V value = load(key);
        put(key, value);
        return value;
    }

    protected abstract V load(K key);
}