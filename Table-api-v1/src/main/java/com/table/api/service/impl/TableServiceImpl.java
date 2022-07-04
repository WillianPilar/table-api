package com.table.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.table.api.service.TableService;
import com.table.api.util.TableUtil;

public class TableServiceImpl implements TableService {
	
	int lastIndexCounterAux = 0;

	@Override
	public List<Integer> processNumbersToList(String numbers) {

		List<Integer> numberList = new ArrayList<Integer>();
		int numberAux = TableUtil.charQuantity(numbers, ",") + 1;

		for (int i = 0; i < numberAux; i++) {

			if (numbers.contains(",")) {

				int index = numbers.indexOf(",");
				String num = "0";

				if (index == 2) {
					num = numbers.substring(index - 2, index);
					numbers = numbers.replaceFirst(numbers.substring(index - 2, index + 1), "").trim();
				} else {
					num = numbers.substring(index - 1, index);
					numbers = numbers.replaceFirst(numbers.substring(index - 1, index + 1), "").trim();
				}

				int number = Integer.valueOf(num);
				numberList.add(number);

			} else {
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
		int columnAndLineValue = TableUtil.calculateColumnAndLine(numberListSize);

		int[][] matriz = new int[columnAndLineValue][columnAndLineValue];
		if (matriz.length > 0) {
			matriz = TableUtil.populateMatriz(matriz, columnAndLineValue, numbersList);
		}
		
		return matriz;
	}

	@Override
	public List<Integer> moveNumbersInNewList(int listSize, int[][] matriz) {

		int columnAndLineValue = TableUtil.calculateColumnAndLine(listSize);
		int lastColumnAndLine = columnAndLineValue - 1;
		List<Integer> listNumbersProcess = new ArrayList<>();
		List<Integer> listAux = new ArrayList<>();
		Boolean getLastIndex = true;
		int lastIndexCounter = 1;

		for (int line = 0; line < columnAndLineValue; line++) {

			for (int column = 0; column < columnAndLineValue; column++) {

				if (line == 0) {

					listNumbersProcess.add(matriz[line][column]);

				} else if (column == lastColumnAndLine && !(line == lastColumnAndLine)) {
					listNumbersProcess.add(matriz[line][lastColumnAndLine]);
				}else if (line == lastColumnAndLine) {
					if (getLastIndex) {
						listNumbersProcess.add(matriz[line][0]);
						getLastIndex = false;
					}else {
						listNumbersProcess.add(listNumbersProcess.size() - lastIndexCounter, matriz[line][column]);
						lastIndexCounter++;
					}
				}else if (!(line == 0 && line == lastColumnAndLine) && column == 0 ) {
					listAux.add(matriz[line][0]);
				}
			}

		}
		
		lastIndexCounterAux = 0;
		listAux.forEach(item -> {
			listNumbersProcess.add(listNumbersProcess.size() - lastIndexCounterAux, item);
			lastIndexCounterAux++;
		});
		
		listNumbersProcess.add(0, listNumbersProcess.get(listNumbersProcess.size() - 1));
		listNumbersProcess.remove(listNumbersProcess.size() - 1);
		
		return listNumbersProcess;
	}

	@Override
	public int[][] moveNumbersInMatriz(List<Integer> newList, int[][] matriz, int primaryListSize) {
		
		int columnAndLineValue = TableUtil.calculateColumnAndLine(primaryListSize);
		int lastColumnAndLine = columnAndLineValue - 1;
		int newListSize = newList.size();
		int countTest = 0;
		
		
		for (int line = 0; line < columnAndLineValue; line++) {
			
			for (int column = 0; column < columnAndLineValue; column++) {
				
				if (line == 0) {
					matriz[line][column] = newList.get(0);
					newList.remove(0);
				}
				
				if (!(line == 0 || line == lastColumnAndLine) && column == lastColumnAndLine) {
					matriz[line][column] = newList.get(0);
					newList.remove(0);
				}
				
				if (line == lastColumnAndLine) {
					
					int indexToAdd = lastColumnAndLine - countTest;
					matriz[line][column] = newList.get(indexToAdd);
					newList.remove(indexToAdd);
					countTest++;
				}
							
			}
			
		}
		
		for (int line = 0; line < columnAndLineValue; line++) {
			for (int column = 0; column < columnAndLineValue; column++) {
				if (!(line == 0 || line == lastColumnAndLine) && column == 0) {
					matriz[line][column] = newList.get(newList.size() - 1);
					newList.remove(newList.size() - 1);
				}
			}
		}
		
		
		return matriz;
	}
}
