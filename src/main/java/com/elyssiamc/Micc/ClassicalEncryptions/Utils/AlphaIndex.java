package com.elyssiamc.Micc.ClassicalEncryptions.Utils;

public class AlphaIndex {
	public static int toInt(char c) {
		if(Character.isAlphabetic(c)) {
			return Character.toLowerCase(c) - 'a';
		}
		return -1;
	}
	
	public static char toChar(int c) {
		return toChar(c, false);
	}
	
	public static char toChar(int c, boolean upper) {
		char ch = (char) (c + 'a');
		if (!upper) {
			ch+=26;
		}
		return ch;		
	}
}
