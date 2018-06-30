
package modele;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec; 

/**
 * Compiled & Edited by Slade Chaos
 * 
 * crypting method
 * author original : billynirvana
 * found here : https://www.developpez.net/forums/d41802/java/general-java/apis/securite/cryptage-decryptage-java/
 * 
 * Compresse method
 * author original : Ralf Quebbemann
 * found here : https://dzone.com/articles/how-compress-and-uncompress
 */
public class Securite {
	
	public static byte[] ouvrirFichierCrypter(String filename) {
		byte[] result = null;
		try {
			result = ouvrirFichier(filename + ".Milk");
			result = decompress(result);
			return decrypter(result);
		} catch (Exception e) {
			System.out.println("Error opening file : " + e.getMessage());
			return null;
		}
	}

	public static void crypterFichier(String filename) {
		try {
			byte[] result = ouvrirFichier(filename +  ".xml");
			result = crypter(result);
			result = compress(result);
			sauverFichier(filename  + ".Milk", result);
		} catch (Exception e) {
			System.out.println("Error saving file  " + e.getMessage());
		}
	}
	
	public static byte[] ouvrirImageCrypter(String filename) {
		byte[] result = null;
		try {
			result = ouvrirFichier(filename + ".Milk");
			return decrypter(result);
		} catch (Exception e) {
			System.out.println("Error opening file  : " + e.getMessage());
			return null;
		}
	}

	public static void crypterImage(String filename, String extension) {
		try {
			byte[] result = ouvrirFichier(filename + extension);
			result = crypter(result);
			sauverFichier(filename  + ".Milk", result);
		} catch (Exception e) {
			System.out.println("Error saving file " + e.getMessage());
		}
	}

	public static byte[] crypter(byte[] aCrypter) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] crypter = null;
		byte[] passwordInBytes = MilkRs.CRYPT_PASS.getBytes(MilkRs.ISO_PASS); 
		Key clef = new SecretKeySpec(passwordInBytes, MilkRs.ALGO); 
		Cipher cipher = Cipher.getInstance(MilkRs.ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, clef);
		crypter = cipher.doFinal(aCrypter);
		return crypter;
	}

	public static byte[] decrypter(byte[] aDecrypter) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] decrypter = null;
		byte[] passwordInBytes = MilkRs.CRYPT_PASS.getBytes(MilkRs.ISO_PASS); 
		Key clef = new SecretKeySpec(passwordInBytes, MilkRs.ALGO); 
		Cipher cipher = Cipher.getInstance(MilkRs.ALGO);
		cipher.init(Cipher.DECRYPT_MODE, clef);
		decrypter = cipher.doFinal(aDecrypter);
		return decrypter;
	}
	
	public static byte[] compress(byte[] data) throws IOException {
		Deflater deflater = new Deflater(); deflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length); 
		deflater.finish(); 
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		} 
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		return output; 
	} 
	
	public static byte[] decompress(byte[] data) throws DataFormatException, IOException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		} 
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		return output; 
	} 
	
	public static byte[] ouvrirFichier(String filename) throws IOException {
		File fichier = new File(filename);
		byte[] result = new byte[(int) fichier.length()];
		FileInputStream in = new FileInputStream(filename);
		in.read(result);
		in.close();
		return result;
	}

	public static void sauverFichier(String filename, byte[] data) throws IOException {
		FileOutputStream out = new FileOutputStream(filename);
		out.write(data);
		out.close();
	}
}
