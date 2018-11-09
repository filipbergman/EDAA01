package textproc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map;


public class GeneralWordCounter implements TextProcessor {
	private Set<String> exeptions = new HashSet<String>();
	private Map<String, Integer> words = new TreeMap<String, Integer>();
	
	
	public GeneralWordCounter(Set<String> stopwords) {
		this.exeptions = stopwords;
	}

	
	@Override
	public void process(String w) {
		if(!exeptions.contains(w) && words.get(w) == null) {
			words.put(w, 1);
		}
		else if (!exeptions.contains(w)) {
			words.put(w, words.get(w) + 1);
		}
	}

	
	@Override
	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
//		for (int i = 0; i < 25; i++) {
//			System.out.println(wordList.get(i));
//		}
		
		for (int i = wordList.size()-1; i > wordList.size()-6; i--) {
			System.out.println(wordList.get(i));
		}
	}
	
	public Set<Map.Entry<String, Integer>> getWords() {
		return words.entrySet();
	}
}
