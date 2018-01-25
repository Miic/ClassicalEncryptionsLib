package com.elyssiamc.Micc.ClassicalEncryptions.Encryptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayfairCipher implements CEncryption {

	private String key;
	private char[][] array = new char[5][5];
	
	public PlayfairCipher(String key) {
		setKey(key.toLowerCase());
	}
	
	public String encrypt(String plainText) {
		if (plainText.length() % 2 != 0) {
			plainText = plainText + "x";
		}
		String[] pairs = Divid2Pairs(plainText);
		String[] crypt = new String[pairs.length];
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < pairs.length; i++) {
			char c1 = pairs[i].charAt(0);
			char c2 = pairs[i].charAt(1);
			
			int x1 = 0;
			int y1 = 0;
			
			int x2 = 0;
			int y2 = 0;
			
			for(int j = 0; j < array.length; j++) {
				for(int k = 0; k < array[j].length; k++) {
					if (array[j][k] == c1 || (array[j][k] == 'i' && c1 == j)) {
						x1 = j;
						y1 = k;
					} else if (array[j][k] == c2 || (array[j][k] == 'i' && c2 == j)) {
						x2 = j;
						y2 = k;
					}
				}
			}
			
			crypt[i] = array[x1][y2] + "" + array[x2][y1];
		}
		for (int i = 0; i < crypt.length; i++) {
			sb.append(crypt[i]);
		}
		
		String result = sb.toString();
		//result = result.replaceAll("x", "");
		
		return result;
	}

	public String decrypt(String plainText) {
		String[] pairs = Divid2Pairs(plainText);
		String[] crypt = new String[pairs.length];
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < pairs.length; i++) {
			char c1 = pairs[i].charAt(0);
			char c2 = pairs[i].charAt(1);
			
			int x1 = 0;
			int y1 = 0;
			
			int x2 = 0;
			int y2 = 0;
			
			for(int j = 0; j < array.length; j++) {
				for(int k = 0; k < array[j].length; k++) {
					if (array[j][k] == c1 || (array[j][k] == 'i' && c1 == j)) {
						x1 = j;
						y1 = k;
					} else if (array[j][k] == c2 || (array[j][k] == 'i' && c2 == j)) {
						x2 = j;
						y2 = k;
					}
				}
			}
			
			crypt[i] = array[x1][y2] + "" + array[x2][y1];
		}
		for (int i = 0; i < crypt.length; i++) {
			sb.append(crypt[i]);
		}
		
		String result = sb.toString();
		result = result.replaceAll("x", "");
		
		return result;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key.toLowerCase();
		
		int counter = 0;
		List<Character> list = new ArrayList<Character>(25); 
		
		for(int i = 0; i < key.length(); i++) {
			//Special Case
			if ((key.charAt(i) == 'i' || key.charAt(i) == 'j') && !list.contains('i')) {
				array[counter / 5][counter % 5] = key.charAt(i);
				list.add('i');
				counter++;
			} else if (!list.contains(key.charAt(i))) {
				array[counter / 5][counter % 5] = key.charAt(i);
				list.add(key.charAt(i));
				counter++;
			}
		}
		for(char i = 'a'; i < 'a' + 27; i++) {
//			System.out.println();
//			for(int j = 0; j < array.length; j++) {
//				System.out.println(Arrays.toString(array[j]));
//			}
			try {
				if ((i == 'i' || i == 'j') && !list.contains('i')) {
					array[counter / 5][counter % 5] = 'i';
					list.add('i');
					counter++;
				} else if (!list.contains(i) && i != 'j') {
					array[counter / 5][counter % 5] = i;
					list.add(i);
					counter++;
				}
			} catch (Exception e) {}
		}
	}
	
    private String[] Divid2Pairs(String new_string) {
        String Original = format(new_string);
        int size = Original.length();
        if (size % 2 != 0)
        {
            size++;
            Original = Original + 'x';
        }
        String x[] = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++)
        {
            x[i] = Original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
    }
    
    private String format(String old_text) {
        int i = 0;
        int len = 0;
        String text = new String();
        len = old_text.length();
        for (int tmp = 0; tmp < len; tmp++)
        {
            if (old_text.charAt(tmp) == 'j')
            {
                text = text + 'i';
            }
            else
                text = text + old_text.charAt(tmp);
        }
        len = text.length();
        for (i = 0; i < len; i = i + 2)
        {
            if (text.charAt(i + 1) == text.charAt(i))
            {
                text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
            }
        }
        return text;
    }

}
