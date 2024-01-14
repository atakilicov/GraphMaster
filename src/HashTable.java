import java.io.*;
import java.util.*;


public class HashTable {

    private static int TABLE_SIZE = 100;
    HashNode[] table;


    public HashTable() {
        table = new HashNode[TABLE_SIZE];
    }

    public void insert(String key, int value) {
        int hash = Hash(key);

        while (table[hash] != null && !table[hash].getKey().equals(key)) {
            hash = (hash + 1) % TABLE_SIZE;
        }

        table[hash] = new HashNode(key, value);
    }

    public int getValue(String key) {
        key=key.toUpperCase();
        int hash = Hash(key);

        while (table[hash] != null && !table[hash].getKey().equals(key)) {
            hash = (hash + 1) % TABLE_SIZE; // Linear probing
        }

        if (table[hash] == null) {
            return -1; // Key not found
        } else {
            return table[hash].getValue();
        }

    }

    public HashNode gethashnode(int index){

        return table[index];
    }

    private int Hash(String key) {
        return Math.abs(key.hashCode() % TABLE_SIZE);

    }

    public static class HashNode {
        private String key;
        private int value;

        public HashNode(String key, int value) {
           this.key = key;
          this.value = value;
        }

        public String getKey() {
            return key.toUpperCase();
        }

        public int getValue() {
            return value;
        }
    }


    }


