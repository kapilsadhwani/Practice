package com.implement.hashing;

public class HashTable {
	private int TABLE_SIZE;
    private int size; 
    private LinkedHashEntry[] table;
 
     /* Constructor */
    public HashTable(int ts) {
        size = 0;
        TABLE_SIZE = ts;
        table = new LinkedHashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    } 
    /* Function to get number of key-value pairs */
    public int getSize(){
        return size;
    }
    /* Function to clear hash table */
    public void makeEmpty(){
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }
    /* Function to get value of a key */
    public int get(String key) {
        int hash = (myhash( key ) % TABLE_SIZE);
        if (table[hash] == null)
            return -1;
        else {
            LinkedHashEntry entry = table[hash];
            while (entry != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry == null)
                return -1;
            else
                return entry.value;
        }
    }
    /* Function to insert a key value pair */
    public void insert(String key, int value) {
        int hash = (myhash( key ) % TABLE_SIZE);
        if (table[hash] == null)
            table[hash] = new LinkedHashEntry(key, value);
        else {
            LinkedHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry.key.equals(key))
                entry.value = value;
            else
                entry.next = new LinkedHashEntry(key, value);
        }
        size++;
    }
 
    public void remove(String key) {
        int hash = (myhash( key ) % TABLE_SIZE);
        if (table[hash] != null) {
            LinkedHashEntry prevEntry = null;
            LinkedHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)) {
                prevEntry = entry;
                entry = entry.next;
            }
            if (entry.key.equals(key)) {
                if (prevEntry == null)
                    table[hash] = entry.next;
                else
                    prevEntry.next = entry.next;
                size--;
            }
        }
    }
    /* Function myhash which gives a hash value for a given string */
    private int myhash(String x ){
        int hashVal = x.hashCode( );
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return hashVal;
    }
    /* Function to print hash table */
    public void printHashTable(){
        for (int i = 0; i < TABLE_SIZE; i++){
            System.out.print("\nBucket "+ (i + 1) +" : ");
            LinkedHashEntry entry = table[i];
            while (entry != null){
                System.out.print(entry.value +" ");
                entry = entry.next;
            }            
        }
    }
}
