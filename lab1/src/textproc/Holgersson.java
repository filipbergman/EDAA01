package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {
	
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		ArrayList<TextProcessor> list = new ArrayList<TextProcessor>();
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>(); // en lämplig mängd skapas
		while (scan.hasNext()) {
			stopwords.add(scan.next());
		}
		TextProcessor stop = new GeneralWordCounter(stopwords);
		
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor p2 = new SingleWordCounter("norge");
		String[] landskap = {"blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
				"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
				"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
				"öland", "östergötland" };
		TextProcessor r = new MultiWordCounter(landskap);

//		list.add(p);
//		list.add(p2);
//		list.add(r);
		list.add(stop);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).process(word);
			}
		}

		s.close();
		
		
		stop.report();
		
		 // kod vars exekveringstid vi vill mäta
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}