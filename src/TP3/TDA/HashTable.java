package TP3.TDA;

import java.util.LinkedList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public abstract class HashTable<K, V> {
    protected List<SimpleEntry<K, V>>[] table;
    private int threshold;

    public HashTable(int capacity) {
        this.threshold = capacity;
        this.table = new List[capacity];
        for(int i = 0; i < capacity; i++){
            this.table[i] = new LinkedList<>();
        }
    }

    public int getThreshold() { return this.threshold; }
    public void setThreshold(int threshold) { this.threshold = threshold; }

    protected int indexFor(K key) {
        int hash = key.hashCode() & 0x7FFFFFFF;
        return hash % this.threshold;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.threshold; i++) {
            result += "[" + i + "] -> ";
            for (SimpleEntry<K, V> entry : this.table[i]) {
                result += entry.getValue() + " → ";
            }
            result += "null\n";
        }
        return result;
    }

    public abstract V get(K key);
    public abstract void put(K key, V value);
    public abstract String showValue(K key);
    public abstract List<V> getAllValues();
}
