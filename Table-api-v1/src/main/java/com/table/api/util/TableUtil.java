package com.table.api.util;

public class TableUtil {

	public static int charQuantity(String stringInput, String charInput) {
		
		int totalCharacters = 0;
		char temp;
		char charCompare = charInput.charAt(0);
		
		for (int i = 0; i < stringInput.length(); i++) {
			temp = stringInput.charAt(i);
			if (temp == charCompare) {
				totalCharacters++;
			}
		}
		
		return totalCharacters;
	}

}
