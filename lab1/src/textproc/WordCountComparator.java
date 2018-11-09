package textproc;
import java.util.*;
import java.util.Map.Entry;
public class WordCountComparator implements Comparator<Map.Entry<String,Integer>> {

	@Override
	public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
		if(arg0.getValue().compareTo(arg1.getValue()) != 0) {
			return arg0.getValue().compareTo(arg1.getValue());
		}
		return arg0.getKey().compareTo(arg1.getKey());
	}
	
}