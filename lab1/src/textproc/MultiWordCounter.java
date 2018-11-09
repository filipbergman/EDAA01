package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> words = new TreeMap<String, Integer>();
	
	
	public MultiWordCounter(String[] words) {
		for (int i = 0; i < words.length; i++) {
			this.words.put(words[i], 0);
		}
	}

	public void process(String w) {
		// TODO Auto-generated method stub
		if (words.containsKey(w)) {
			words.put(w, words.get(w) + 1);
		}
	}

	public void report() {
		// TODO Auto-generated method stub
		for (String key : words.keySet()) {
			System.out.println(key + ": " + words.get(key));
		}
	}
}
