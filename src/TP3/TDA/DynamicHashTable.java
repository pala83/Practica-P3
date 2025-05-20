package TP3.TDA;

import java.util.LinkedList;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class DynamicHashTable<K, V> extends HashTable<K, V> {
    private int size;
    private final double loadFactor = 0.9;

    public DynamicHashTable(int capacity) {
        super(capacity);
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        if((double)(this.size/this.getThreshold()) >= this.loadFactor){
            rehash();
        }
        int index = this.indexFor(key);
        this.table[index].add(new SimpleEntry<>(key, value));
        size++;
    }

    private void rehash() {
        int newThreshold = this.getThreshold() * 2 + 1;
        System.out.println("Rehashing from " + this.getThreshold() + " to " + newThreshold);
        List<SimpleEntry<K, V>>[] newTable = new List[newThreshold];
        for(int i = 0; i < newThreshold; i++){
            newTable[i] = new LinkedList<>();
        }
        for (List<SimpleEntry<K, V>> bucket : this.table) {
            for (SimpleEntry<K, V> entry : bucket) {
                int index = indexFor(entry.getKey());
                newTable[index].add(entry);
            }
        }
        this.setThreshold(newThreshold);
        this.table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(key);
        for (SimpleEntry<K, V> e : this.table[index]) {
            if(e.getKey().equals(key)) return e.getValue();
        }
        return null;
    }

    @Override
    public String showValue(K key){
        V value = this.get(key);
        return value!=null?value.toString():"No se encontro el valor";
    }

    @Override
    public List<V> getAllValues(){
        List<V> values = new LinkedList<>();
        for (List<SimpleEntry<K, V>> bucket : this.table) {
            for (SimpleEntry<K, V> entry : bucket) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    @Override
    public String toString() {
        return "Tabla de hash (Separado con crecimiento) con M = " + this.getThreshold() + " pd=" + this.loadFactor + " :\n" + super.toString();
    }
}
