package com.table.api.util;

import java.util.List;

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

	public static int calculateColumnAndLine(int numberListSize) {
		
		int columnAndLineValue = 0;

		for (int i = 1; i < numberListSize; i++) {

			int iSquared = i * i;

			if (iSquared > numberListSize) {
				i = numberListSize + 1;
			}

			if (iSquared == numberListSize) {
				columnAndLineValue = i;
				i = numberListSize + 1;
			}

		}
		
		return columnAndLineValue;
	}

	public static int[][] populateMatriz(int[][] matriz, int columnAndLineValue, List<Integer> numbersList) {

		int indexNumber = 0;

		for (int line = 0; line < columnAndLineValue; line++) {

			for (int column = 0; column < columnAndLineValue; column++) {
				matriz[line][column] = numbersList.get(indexNumber);
				indexNumber++;
			}

		}

		return matriz;
	}

}
