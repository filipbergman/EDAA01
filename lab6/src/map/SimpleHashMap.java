package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int capacity;
	private double loadFactor;
	private int size;

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap() {
		loadFactor = 0.75;
		capacity = 16;
		size = 0;
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap(int capacity) {
		loadFactor = 0.75;
		this.capacity = capacity;
		size = 0;
		table = (Entry<K, V>[]) new Entry[this.capacity];
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
				s += temp.toString();
				temp = temp.next;
			}
			s += "\n";
		}
		return s;
	}

	@Override
	public V get(Object key) {
		key = (K) key;
		for(int i = 0; i < table.length; i++) {
			Entry<K, V> temp = table[i];
			while(table[i] != null && temp != null) {
				if(key.equals(temp.key)) {
					return temp.value;
				}
				temp = temp.next;
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public V put(K key, V newVal) {
		Entry<K, V> entry = new Entry<K, V>(key, newVal);
		int index = index(key);
		Entry<K, V> temp = table[index];

		// Behövs bara kollas en gång, därför ligger den utanför loopen.
		if (table[index] == null) {
			table[index] = entry;
			size++;
			if (size() > table.length*loadFactor) {
				rehash();
			}
			return null;
		} else {
			while (true) {
				if (temp.key.equals(key)) {
					V tempVal = temp.value;
					temp.value = entry.value;
					return tempVal;
				} else if (temp.next == null) {
					temp.next = entry;
					size++;
					return null;
				}
				if (size() > table.length*loadFactor) {
					rehash();
				}
				temp = temp.next;
			}
		}
	}

	private void rehash() {
		size = 0;
		capacity = capacity * 2;
		Entry<K, V>[] tempList = (Entry<K, V>[]) new Entry[capacity];
		for (int i = 0; i < table.length; i++) {
			while (table[i] != null) {
				tempList[i] = table[i];
				table[i] = table[i].next;
			}
		}
		table = (Entry<K, V>[]) new Entry[capacity];
		for (int i = 0; i < tempList.length; i++) {
			Entry<K, V> temp = tempList[i];
			while(temp != null) {
				if(tempList[i] != null) {
					put(temp.getKey(), temp.getValue());
				}
				temp = temp.next;
			}
		}
	}

	@Override
	public V remove(Object arg0) {
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
