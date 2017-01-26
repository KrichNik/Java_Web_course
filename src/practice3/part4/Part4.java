package practice3.part4;

import java.security.*;
import java.util.Arrays;

public class Part4 {

	public static void main(String[] args) {
		try {
			System.out.println(hash("password", "SHA-256"));
			System.out.println(hash("passwort", "SHA-256"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
	}

	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(input.getBytes());
		byte[] hash = digest.digest();
		String[] binary = new String[hash.length];
		String[] hex = new String[hash.length];
		for (int i = 0; i < hash.length; i++) {
			binary[i] = toBinary(hash[i]);
		}
		for (int i = 0; i < hash.length; i++) {
			hex[i] = toHex(binary[i]);
		}
		return Arrays.toString(hash) + System.lineSeparator() + Arrays.toString(hex);
	}

	public static String toBinary(int a) {
		String binary = Integer.toBinaryString(a);
		if (binary.length() > 8) {
			return binary.substring(binary.length() - 8);
		} else if (binary.length() < 8) {
			String temp = "00000000";
			return temp.substring(binary.length()) + binary;
		} else {
			return binary;
		}
	}

	public static String toHex(String binary) {
		String part1 = Integer.toHexString(binary2digest(binary.substring(0, 4)));
		String part2 = Integer.toHexString(binary2digest(binary.substring(4)));
		return part1 + part2;
	}

	public static int binary2digest(String binary) {
		int digest = 0;
		if (binary.substring(0, 1).equals("1")) {
			digest += 8;
		}
		if (binary.substring(1, 2).equals("1")) {
			digest += 4;
		}
		if (binary.substring(2, 3).equals("1")) {
			digest += 2;
		}
		if (binary.substring(3).equals("1")) {
			digest += 1;
		}
		return digest;
	}
}
