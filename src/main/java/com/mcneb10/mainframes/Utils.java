package com.mcneb10.mainframes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;

public class Utils {
	private static Utils INSTANCE = new Utils();
	private static String[] words;
	private static Random random = new Random();
	public Utils() {
		int index = 0;
		words = new String[32];
		try {
			InputStream stream = getClass().getResourceAsStream("names.txt");
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader breader = new BufferedReader(reader);
			String line;

			while ((line = breader.readLine()) != null) {
				words[index] = line.trim();
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Utils getInstance() {
		return INSTANCE;
	}
	
	public String generateNewMainframeName() {
		return randomName() + " " + convertToRomanNumerals(random.nextInt(20));
	}

	public String randomName() {
		return words[random.nextInt(words.length)];
	}
	
	public String convertToRomanNumerals(int input) {
		if (input >= 4000)
			return ""; // number too big
		if (input < 1)
			return ""; // number too small
		String result = "";
		while (input > 0) {
			if (input >= 1000) {
				result += "M";
				input -= 1000;
			} else if (input >= 900) {
				result += "CM";
				input -= 900;
			} else if (input >= 500) {
				result += "D";
				input -= 500;
			} else if (input >= 400) {
				result += "CD";
				input -= 400;
			} else if (input >= 100) {
				result += "C";
				input -= 100;
			} else if (input >= 90) {
				result += "XC";
				input -= 90;
			} else if (input >= 50) {
				result += "L";
				input -= 50;
			} else if (input >= 40) {
				result += "XL";
				input -= 40;
			} else if (input >= 10) {
				result += "X";
				input -= 10;
			} else if (input >= 9) {
				result += "IX";
				input -= 9;
			} else if (input >= 5) {
				result += "V";
				input -= 5;
			} else if (input >= 4) {
				result += "IV";
				input -= 4;
			} else if (input >= 1) {
				result += "I";
				input -= 1;
			}

		}
		return result;
	}
}
