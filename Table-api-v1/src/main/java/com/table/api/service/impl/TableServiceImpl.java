package com.table.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.table.api.service.TableService;
import com.table.api.util.TableUtil;

public class TableServiceImpl implements TableService{

	@Override
	public List<Integer> processNumbersToList(String numbers) {
		
		List<Integer> numberList = new ArrayList<Integer>();
		int numberAux = TableUtil.charQuantity(numbers, ",") + 1;
		
		for (int i = 0; i < numberAux; i++) {
			
			if (numbers.contains(",")) {
				
				int index = numbers.indexOf(",");
				String num = "0";

				if( index == 2) {
					num  = numbers.substring(index - 2, index);
					numbers = numbers.replaceFirst(numbers.substring(index - 2, index + 1), "").trim();
				}else {
					num  = numbers.substring(index - 1, index);
					numbers = numbers.replaceFirst(numbers.substring(index - 1, index + 1), "").trim();
				}
				
				int number = Integer.valueOf(num);
				numberList.add(number);
				
			}else {
				numbers = numbers.trim();
				if (!numbers.isBlank()) {
					numberList.add(Integer.valueOf(numbers));
				}
			}
			
		}
		
		return numberList;
	}

	@Override
	public int[][] processListToMatriz(List<Integer> numbersList) {
		
		int numberListSize = numbersList.size();
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
		
		int[][] matriz = new int[columnAndLineValue][columnAndLineValue];
		
		if (matriz.length > 0) {
			matriz = this.popularMatriz(matriz, columnAndLineValue, numbersList);
		}
		
		return matriz;
	}

	@Override
	public int[][] popularMatriz(int[][] matriz, int columnAndLineValue, List<Integer> numbersList) {
		
		int indexNumber = 0;
		
		for (int line = 0; line < columnAndLineValue; line++) {
			
			for (int column = 0; column < columnAndLineValue; column++) {
				matriz[line][column] = numbersList.get(indexNumber);
				indexNumber++;
			}
			
		}
		
		
		return matriz;
	}

	@Override
	public int[][] moveNumbersInMatriz(List<Integer> numbersList, int[][] matriz) {
		// TODO Auto-generated method stub
		return null;
	}


}
