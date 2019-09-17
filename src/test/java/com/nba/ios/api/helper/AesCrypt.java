package com.nba.ios.api.helper;

import java.security.Key;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * To perform encryption decryption.
 * 
 *
 */
public final class AesCrypt {

  /**
   * constructor.
   */
  private AesCrypt() {
    //constructor.
  }

  /**
   * algorith.
   */
  private static final String ALGORITHM = "AES";

  /**
   * key.
   */
  private static final String KEY = "1Hbfh667adfDEJ78";

  /**
   * To encrypt a given string.
   * @param value value
   * @return String String
   * @throws Exception Exception
   */
  public static String encrypt(final String value) throws Exception {
   
    Key key = generateKey();
    Cipher cipher = Cipher.getInstance(AesCrypt.ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
   // byte[] encryptedByteValue = cipher.doFinal(value.getBytes("UNICODE_FORMAT"));
    String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
    return encryptedValue64;

  }

  /**
   * To decrypt a given string.
   * @param value value
   * @return String String
   * @throws Exception Exception
   */
  public static String decrypt(final String value) throws Exception {
    Key key = generateKey();
    Cipher cipher = Cipher.getInstance(AesCrypt.ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
    byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
    String decryptedValue = new String(decryptedByteValue, "utf-8");
    return decryptedValue;

  }

  /**
   * generateKey.
   * @return Key Key
   * @throws Exception Exception
   */
  private static Key generateKey() throws Exception {
    Key key = new SecretKeySpec(AesCrypt.KEY.getBytes(), AesCrypt.ALGORITHM);
    return key;
  }

  /*
   * public static void main(String[] args) throws Exception { // String
   * encryptedkey = encrypt("10203040"); }
   */
  
public static void main(String args[]) throws Exception {
	
	/*Scanner scanIn = new Scanner(System.in);
	System.out.println("Enter your password : ");
	System.out.println("\n");
	
	String userInput = scanIn.next();
	System.out.println("Password "+userInput);*/
	
	
	String password = encrypt("123456");
	 System.out.println("Encrypted :: "+password);
	 
	 String decrypt = decrypt("gDzNC/97HsLYVxeya6hRvQ==");
	 System.out.println("Decrypted :: " +decrypt);
	  
  }

}
