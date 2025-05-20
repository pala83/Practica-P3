package TP3.TDA;

import java.util.LinkedList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class StaticHashTable<K, V> extends HashTable<K, V> {

    public StaticHashTable(int capacity) {
        super(capacity);
    }

    @Override
    public V get(K key) {
        int index = this.indexFor(key);
        for (SimpleEntry<K, V> e : this.table[index]) {
            if(e.getKey().equals(key)) return e.getValue();
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int index = this.indexFor(key);
        this.table[index].add(new SimpleEntry<>(key, value));
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
        return "Tabla de hash (Separado) con M = " + this.getThreshold() + ":\n" + super.toString();
    }

}
