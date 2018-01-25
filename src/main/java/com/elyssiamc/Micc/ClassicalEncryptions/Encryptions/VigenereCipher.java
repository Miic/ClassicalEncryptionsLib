package com.elyssiamc.Micc.ClassicalEncryptions.Encryptions;

public class VigenereCipher implements CEncryption {
	
	private CeaserCipher cCipher = new CeaserCipher(0);
	private String key;
	
	public VigenereCipher(String key) {
		setKey(key);
	}

	public String encrypt(String plainText) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < plainText.length(); i++) {
			cCipher.setKey( (key.charAt(i % key.length()) - 'a') + "" );
			sb.append(cCipher.encrypt((plainText.charAt(i) + "")));
		}
		return sb.toString();
	}

	public String decrypt(String cipherText) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < cipherText.length(); i++) {
			cCipher.setKey((key.charAt(i % key.length()) - 'a') + "");
			sb.append(cCipher.decrypt((cipherText.charAt(i) + "")));
		}
		return sb.toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key.toLowerCase();
	}

}
