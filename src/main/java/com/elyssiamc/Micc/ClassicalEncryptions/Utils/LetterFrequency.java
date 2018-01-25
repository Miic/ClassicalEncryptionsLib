package com.elyssiamc.Micc.ClassicalEncryptions.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

public class LetterFrequency {
	public static final char[] FREQUENCY = "etaoinshrdlcumwfgypbvkjxqz".toCharArray();
	
	public static Trie<String, Integer> FREQUENCYHY = new PatriciaTrie<Integer>();
	
	public static void init() throws FileNotFoundException {
		if (FREQUENCYHY.size() == 0) {
			File folder = new File("src/main/java/com/elyssiamc/Micc/ClassicalEncryptions/ngrams/");
			File[] files = folder.listFiles();
			for (int i = 0; i < files.length; i++) {
				Scanner s = new Scanner(files[i]);
				while (s.hasNextLine()) {
					String token = s.nextLine();
					String[] tokens = token.split(" ");
					FREQUENCYHY.put(tokens[0], Integer.parseInt(tokens[1]));
				}
				s.close();
			}
		}
	}
	
	public static char[] randomizeSwap(char[] array, int x) {

		Random random = new Random();
		char[] array1 = array.clone();
		
		for(int i = 0; i < x; i++) {
			int x1 = 0;
			int x2 = 0;
			while (x1 == x2) {
				x1 = random.nextInt(array.length);
				x2 = random.nextInt(array.length);
			}
			array1 = swap(array, x1, x2);
		}
		return array1;
	}
	
	private static char[] swap(char[] array, int x1, int x2) {
		try {
			if (x1 != x2) {
				char[] arr = array.clone();
				char x = array[x1];
				arr[x1] = arr[x2];
				arr[x2] = x;
				return arr;
			}
		} catch (Exception e) {}
		return null;
	}

	public static char[] toFrequency(String input) {
		Hashtable<Character, Integer> cr = new Hashtable<Character, Integer>();
		for(int i = 0; i < input.length(); i++) {
			if (!cr.containsKey(input.charAt(i))) {
				cr.put(input.charAt(i), 0);
			}
			cr.put(input.charAt(i), cr.get(input.charAt(i)) + 1);
		}
		List<Entry<Character, Integer>> list = new LinkedList<Entry<Character, Integer>>(cr.entrySet());
	    Collections.sort(list, new Comparator() {
	    	public int compare(Object o1, Object o2) {
	    		return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
	    	}
	    });
	    char[] returnL = new char[list.size()];
	    for(int i = 0; i < list.size(); i++) {
	    	returnL[i] = list.get(i).getKey();
	    }
		return returnL;
	}
	
	public static boolean wordsContainVowels(String string) {
		String[] array = string.toLowerCase().split(" ");
		for(String token : array) {
			if (!(token.contains("a") || token.contains("e") || token.contains("i") || token.contains("o") || token.contains("u") || token.contains("y")))
				return false;
		}
		return true;
	}
	
	public static int getFitness(String string) {
		int counter = 0;
		
		for(int i = 0; i < 5; i++) {
			int x = 0;
			for(int j = 0; j < string.trim().length() - i; j++) {
				String sub = string.trim().substring(j, j+i);
				if (sub.length() > 0 && FREQUENCYHY.get(sub) != null) {
					System.out.println(FREQUENCYHY.get(sub));
					x += FREQUENCYHY.get(sub);
				}
			}
//			if (i != 0) {
//				x /= i;
//			}
			counter += x;
		}
		
		return counter;
	}
	
    public static List<?> sortValue(Hashtable<?, Integer> t){

        //Transfer as List and sort it
        ArrayList<Map.Entry<?, Integer>> l = new ArrayList(t.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<?, Integer>>(){

          public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
             return o1.getValue().compareTo(o2.getValue());
         }});
        
        return l;
   }
    
    public static String demono(String input, char[] key, char[] frequency) {
    	char[] in = input.toCharArray();
    	boolean[] bool = new boolean[input.length()];
    	
    	for(int i = 0; i < frequency.length; i++) {
    		for (int j = 0; j < in.length; j++) {
	    		if (frequency[i] == in[j] && !bool[j]) {
	    			in[j] = key[j];
	    			bool[j] = true;
	    		}
    		}
    	}
    	
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < in.length; i++) {
    		sb.append(in[i]);
    	}
    	
    	return sb.toString();
    }
}
