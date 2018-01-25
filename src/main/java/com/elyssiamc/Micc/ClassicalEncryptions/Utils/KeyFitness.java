package com.elyssiamc.Micc.ClassicalEncryptions.Utils;

public class KeyFitness {
	public int score;
	public char[] key;
	
	public KeyFitness(char[] key, int score) {
		this.key = key;
		this.score = score;
	}
}
