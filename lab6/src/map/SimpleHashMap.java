package map;

import java.util.HashSet;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int capacity;
	private double loadFactor;
	private int size;

	public static void main(String args[]) {
		SimpleHashMap<Integer, Integer> shm = new SimpleHashMap<Integer, Integer>(10);

		java.util.Random random = new java.util.Random(123456);
		for (int i = 0; i < 10; i++) {
			int r = random.nextInt(1000 + 1 + 1000) - 1000;
			shm.put(r, r);
		}

		System.out.println(shm.show());
		System.out.println("Capacity: " + shm.capacity);
		System.out.println("Size: " + shm.size());
		System.out.println("Fyllnadsgrad: " + (double) shm.size() / shm.capacity);

	}

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap() {
		loadFactor = 0.75;
		capacity = 16;
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0; 
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap(int capacity) {
		loadFactor = 0.75;
		this.capacity = capacity;
		table = (Entry<K, V>[]) new Entry[this.capacity];
		size = 0;
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % table.length);
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> temp = table[index];
		while (temp != null) {
			if (temp.key.equals(key)) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	public String show() {
		String s = new String();
		for (int i = 0; i < table.length; i++) {
			s += i + "\t";
			Entry<K, V> temp = table[i];
			while (temp != null) {
				s += temp.toString() + "\t";
				temp = temp.next;
			}
			s += "\n";
		}
		return s;
	}

	@Override
	public V get(Object key) {
		Entry<K, V> found = find(index((K) key), (K) key);
		if (found == null)
			return null;
		else
			return found.value;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V newVal) {
		Entry<K, V> entry = new Entry<K, V>(key, newVal);
		int index = index(key);
		Entry<K, V> temp = table[index];

		// Behövs bara kollas en gång, därför ligger den utanför loopen.
		if (temp == null) {
			table[index] = entry;
			size++;
			if (size() > table.length * loadFactor) {
				rehash();
			}
			return null;
		} else {
			while(true) {
				if (temp.key.equals(key)) {
					V tempVal = temp.value;
					temp.value = newVal;
					if (size() > table.length * loadFactor) {
						rehash();
					}
					return tempVal;
				} else if (temp.next == null) {
					temp.next = entry;
					size++;
					if (size() > table.length * loadFactor) {
						rehash();
					}
					return null;
				}
				temp = temp.next;
			}
		}
	}

	private void rehash() {
		size = 0;
		// Skapar först en kopia av table
		Entry<K, V>[] tempList = table;
		capacity = capacity * 2;
		Entry<K, V> temp;

		// Gör sedan en ny table som är dubbelt så stor.
		table = (Entry<K, V>[]) new Entry[capacity];

		// Lägger sedan in elementen från tempList till den nya table genom put.
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i] != null) {
				temp = tempList[i];
				while (temp != null) {
					put(temp.getKey(), temp.getValue());
					temp = temp.next;
				}
			}
		}
	}

	@Override
	public V remove(Object key) {
		int index = index((K) key);
		Entry<K, V> temp = table[index];
		Entry<K, V> prevTemp = null;
		Entry<K, V> found = find(index, (K) key);

		// Om listan inte är tom && listan innehåller det sökta elementet
		if (found != null) {
			V tempVal = found.value;
			// Det är ett elementet i listan
			if (temp.next == null) {
				table[index] = null;
				size--;
				return tempVal;
			}
			// Elementet är senare i listan
			else {
				while(true) {
					prevTemp = temp;
					temp = temp.next;
					// Det förra elementets next blir detta elementets next (kan vara null).
					if (temp.key.equals(key)) {
						prevTemp.next = temp.next;
						table[index].next = null;
						size--;
						return tempVal;
					}
				}
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		private Entry(K k, V v) {
			key = k;
			value = v;
			next = null;
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
			V temp = value;
			this.value = value;
			return temp;
		}

		@Override
		public String toString() {
			return getKey().toString() + " = " + getValue().toString();
		}
	}
}
