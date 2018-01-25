package com.elyssiamc.Micc.ClassicalEncryptions.Encryptions;

public interface CEncryption {
		
	public String encrypt(String plainText);
	public String decrypt(String cipherText);
	
	public String getKey();
	public void setKey(String key);
	
}
