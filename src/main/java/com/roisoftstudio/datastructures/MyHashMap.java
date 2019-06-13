package com.roisoftstudio.datastructures;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {

    private final int INITIAL_CAPACITY = 10;
    private float loadFactor = 0.75f;
    private Node<K, V>[] buckets;
    private int size = 0;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        capacity = INITIAL_CAPACITY;
        buckets = (Node<K, V>[]) new Node[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object objectKey) {
        K key = (K) objectKey;


        Node<K, V> bucket = buckets[obtainIndex(key)];
        if (bucket != null) {
            return bucket.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int bucketIndex = obtainIndex(key);
        Node<K, V> node = buckets[bucketIndex];

        if (node == null) {
            buckets[bucketIndex] = new Node<>(key, value, null);
        } else {
            if (node.key == key) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }

            Node<K, V> previous = node;
            node = node.next;
            while (node != null && node.key != key) {
                previous = node;
                node = node.next;
            }
            if (node == null) {
                previous.next = new Node<>(key, value, null);
            } else {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }

        size++;
        if (size / capacity > loadFactor) {
            //resize();
        }
        return null;
    }

    private int obtainIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }


    public static class Node<K, V> implements Entry<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

}
