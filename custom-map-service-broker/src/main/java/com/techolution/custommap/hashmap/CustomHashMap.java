package com.techolution.custommap.hashmap;

import java.util.ArrayList;
import java.util.List;

public class CustomHashMap<K, V> {

	private static final int DEFAULT_CAPACITY = 10;
	private KeyValue<K, V>[] keyValues; // array of key values
	private int capacity; // capacity of HashMap

	/**
	 * Create CustomHashMap with default initial capacity;
	 */
	@SuppressWarnings("unchecked")
	public CustomHashMap() {
		this.capacity = DEFAULT_CAPACITY;
		keyValues = new KeyValue[DEFAULT_CAPACITY];
	}

	/**
	 * Create Custom Map with initial capacity.
	 * 
	 * @param initialCapacity
	 *            initial capacity
	 */
	@SuppressWarnings("unchecked")
	public CustomHashMap(int initialCapacity) {
		this.capacity = initialCapacity;
		keyValues = new KeyValue[initialCapacity];
	}

	public int getCapacity() {
		return capacity;
	}

	/**
	 * Finds the size of the map.
	 * 
	 * @return size of the map
	 */
	public int size() {
		int count = 0;
		for (KeyValue<K, V> entry : keyValues) {
			// check all the linked entries
			count += checkNextEntriesRecursively(entry);
		}
		return count;
	}

	private int checkNextEntriesRecursively(KeyValue<K, V> entry) {
		int count = 0;
		if (entry != null) {
			count++;
			count += checkNextEntriesRecursively(entry.getNext());
		}
		return count;
	}

	/**
	 * find value for specified key.
	 * 
	 * @param key
	 */
	public V find(K key) {
		int bucket = hashFunction(key);
		if (keyValues[bucket] == null) {
			return null;
		} else {
			KeyValue<K, V> temp = keyValues[bucket];
			while (temp != null) {
				if (temp.getKey().equals(key)) {
					return temp.getValue();
				}
				temp = temp.getNext(); // return value corresponding to key.
			}
			return null; // returns null if key is not found.
		}
	}

	
	public List<V> findAll() {
		List<V> values = new ArrayList<>();
		for (KeyValue<K, V> keyValue : keyValues) {
			KeyValue<K, V> temp = keyValue;
			while (temp != null) {
				values.add(temp.getValue());
				temp = temp.getNext(); // return value corresponding to key.
			}
		}
		return values;
	}

	/**
	 * Stores key-value pair in CustomHashMap.
	 * 
	 * if key already exists - updates the value else creates new entry and
	 * stores value.
	 * 
	 * @param key
	 * @param value
	 */
	public void store(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("CustomHashMap does not allow to store null");
		}

		// calculate hash of key.
		int bucket = hashFunction(key);

		// create new entry
		KeyValue<K, V> newEntry = new KeyValue<K, V>(key, value, null);

		// if table location does not contain any entry, store entry there.
		if (keyValues[bucket] == null) {
			keyValues[bucket] = newEntry;
		} else {
			KeyValue<K, V> previous = null;
			KeyValue<K, V> current = keyValues[bucket];

			// iterate through all next elements of current element
			while (current != null) {
				// if key is matching
				if (current.getKey().equals(key)) {
					if (previous == null) {
						// node has to be inserted on first of bucket.
						newEntry.setNext(current.getNext());
						keyValues[bucket] = newEntry;
						return;
					} else {
						newEntry.setNext(current.getNext());
						previous.setNext(newEntry);
						return;
					}
				}
				previous = current;
				current = current.getNext();
			}
			previous.setNext(newEntry);
		}
	}

	/**
	 * Calculates the grid location by applying hash function on key.
	 * 
	 * @param key
	 */
	private int hashFunction(K key) {
		return (Math.abs(key.hashCode()) % capacity);
	}

	/**
	 * removes key-value pair from HashMapCustom.
	 * 
	 * @param key
	 */
	public boolean remove(K key) {

		int bucket = hashFunction(key);

		if (keyValues[bucket] == null) {
			return false;
		} else {
			KeyValue<K, V> previous = null;
			KeyValue<K, V> current = keyValues[bucket];

			while (current != null) {
				if (current.getKey().equals(key)) {
					if (previous == null) { // delete first entry node.
						keyValues[bucket] = keyValues[bucket].getNext();
						return true;
					} else {
						previous.setNext(current.getNext());
						return true;
					}
				}
				previous = current;
				current = current.getNext();
			}
			return false;
		}

	}

	private static class KeyValue<K, V> {
		private K key;
		private V value;
		private KeyValue<K, V> next;

		public KeyValue(K key, V value, KeyValue<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public KeyValue<K, V> getNext() {
			return next;
		}

		public void setNext(KeyValue<K, V> next) {
			this.next = next;
		}

	}

}
