package com.elyssiamc.Micc.ClassicalEncryptions;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

import com.elyssiamc.Micc.ClassicalEncryptions.Encryptions.*;
import com.elyssiamc.Micc.ClassicalEncryptions.Utils.KeyFitness;
import com.elyssiamc.Micc.ClassicalEncryptions.Utils.LetterFrequency;

public class App 
{
    public static void main( String[] args )
    {
    	Scanner kb = new Scanner(System.in);
    	
    	do {
    		System.out.println("\nOptions:\n  1.Ceaser Cipher\n  2.Playfair Cipher\n  3.Vigenere Cipher\n  4.Monoalphabetic Analysis\n  5.Exit \n\nEnter option #: ");
    		
    		CEncryption cc = null;
	        String input = null;

    		
    		String lineToken = kb.nextLine();
    		
    		if (lineToken.equals("1")) {
    			do {
    				System.out.println("Enter Key: ");
    				input = kb.nextLine();
    				try {
    					cc = new CeaserCipher(Integer.parseInt(input));
    				} catch (Exception e) {
    					input = null;
    					System.out.println("Invalid Key! Try again!");
    				}
    			} while (input == null);
    			input = null;
    			do {
    				System.out.println("Enter Plain Text: ");
    				input = kb.nextLine();
    			} while (input == null);
    		} else if (lineToken.equals("2")) {
    			do {
    				System.out.println("Enter Key: ");
    				input = kb.nextLine();
    				try {
    					cc = new PlayfairCipher(input);
    				} catch (Exception e) {
    					input = null;
    					e.printStackTrace();
    				}
    			} while (input == null);
    			input = null;
    			do {
    				System.out.println("Enter Plain Text: ");
    				input = kb.nextLine();
    			} while (input == null);
    		} else if (lineToken.equals("3")) {
    			do {
    				System.out.println("Enter Key: ");
    				input = kb.nextLine();
    				try {
    					cc = new VigenereCipher(input);
    				} catch (Exception e) {
    					input = null;
    				}
    			} while (input == null);
    			input = null;
    			do {
    				System.out.println("Enter Plain Text: ");
    				input = kb.nextLine();
    			} while (input == null);
    		} else if (lineToken.equals("4")) {
    			System.out.println("Enter Monoalphabetic String for Cryptoanalysis: ");
    			input = kb.nextLine();
    		} else if (lineToken.equals("5")) {
    			System.out.println("Exiting... Bye");
    			System.exit(0);
    		} else {
    			System.out.println("Unknown Command: " + lineToken);
    		}
    		
    		if (cc != null) {
		    	System.out.println( "Input String: " + input);
		        
		    	final String cipher = cc.encrypt(input);
		    	System.out.println("Cipher Text: " + cipher);
		    	
		    	final String plain = cc.decrypt(cipher);
		    	System.out.println("Decrypted Text: " + plain);
    		} else {
    			try {
    				System.out.println("\nLoading N-Grams Libraries for Statsitical Analysis... ");
					LetterFrequency.init();
					System.out.println("Library Loaded with " + LetterFrequency.FREQUENCYHY.size() + " gram entries");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					continue;
				}
    			
    			System.out.println("Now performing 100 Iterations of Genetic Algorithm for Top 5 English Monoalphabetic Possibilities.");
    			
    			Random random = new Random();
    			PriorityQueue<KeyFitness> queue = new PriorityQueue<KeyFitness>(100, new Comparator<KeyFitness>() {

					public int compare(KeyFitness o1, KeyFitness o2) {
						return o1.score - o2.score;
					}
    				
    			});
    			PriorityQueue<KeyFitness> top = new PriorityQueue<KeyFitness>(100, new Comparator<KeyFitness>() {

					public int compare(KeyFitness o1, KeyFitness o2) {
						return o2.score - o1.score;
					}
    				
    			});
    			char[] topLetters = LetterFrequency.toFrequency(input);
    			
    			for (int a = 0; a < 100; a++) {
	    			char[][] keys = new char[100][26];
	    			for(int i = 0; i < keys.length; i++) {
	    				keys[i] = LetterFrequency.randomizeSwap(LetterFrequency.FREQUENCY, 26);
	    			}
	    			
	    			for(int i = 0; i < 1000; i++) {
	    				for(int k = 0; k < keys.length; k++) {
		    				String strang = new String(input);
		    				strang = LetterFrequency.demono(input, keys[k], topLetters);
		    				queue.add(new KeyFitness(keys[k], LetterFrequency.getFitness(strang)));
	    				}
	    				for(int k = 0; k < 5; k++) {
	    					keys[k] = queue.poll().key;
	    				}
	    				for(int k = 5; k < keys.length; k++) {
	    					keys[k] = LetterFrequency.randomizeSwap(keys[random.nextInt(5)], random.nextInt(5));
	    				}
	    				if (i != 1000 - 1)
	    					queue.clear();
	    			}
	    			for(int i = 0; i < 5; i++) {
	    				top.add(queue.poll());
	    			}
    			}
    			
    			System.out.println("Top 5 Results from 1000 iterations using n-gram fitness + genetic algorithm");
    			for(int i = 0; i < 5; i++) {
    				KeyFitness fit = top.poll();
    				String strang = new String(input);
    				strang = LetterFrequency.demono(strang, fit.key, topLetters);
    				System.out.println(i + ") " + strang + " - Fitness: " + LetterFrequency.getFitness(strang));
    			}
    			
    		}
    	} while (true);
    }
}
