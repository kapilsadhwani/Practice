package com.implement.hashing;

public class LinkedHashEntry {
	String key;
    int value;
    LinkedHashEntry next;
 
    /* Constructor */
    LinkedHashEntry(String key, int value){
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
