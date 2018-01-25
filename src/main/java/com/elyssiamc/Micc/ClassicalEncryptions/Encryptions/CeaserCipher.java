package com.elyssiamc.Micc.ClassicalEncryptions.Encryptions;

public class CeaserCipher implements CEncryption {

	private int key;
	
	public CeaserCipher(int key) {
		this.key = key % 26;
	}
	
	public String encrypt(String plainText) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < plainText.length(); i++) {
		 	sb.append( (char) (((plainText.charAt(i) + key - 'a') % 26) + 'a') );
		}
		return sb.toString();
	}

	public String decrypt(String cipherText) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < cipherText.length(); i++) {
		 	sb.append( (char) (((cipherText.charAt(i) - key - 'a') % 26) + 'a') );
		}
		return sb.toString();
	}

	public String getKey() {
		return key + "";
	}

	public void setKey(String key) {
		try {
			this.key = Integer.parseInt(key) % 26;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
