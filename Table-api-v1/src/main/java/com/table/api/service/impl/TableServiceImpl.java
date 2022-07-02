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
				int number = Integer.valueOf(numbers.substring(index - 1, index));
				numbers = numbers.replace(numbers.substring(index - 1, index + 1), "");
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
		
		for (int i = 0; i < numberListSize; i++) {
			
			int iSquared = i * i;
			
			if (iSquared > numberListSize) {
				i = numberListSize + 1;
			}
			
			if (iSquared == numberListSize) {
				i = numberListSize + 1;
				columnAndLineValue = iSquared;
			}
			
		}
		
		return new int[columnAndLineValue][columnAndLineValue];
	}

}
