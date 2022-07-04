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
		
		/* Retorna a quantidade de ','
		 * Quantidade de ',' + 1 = Quantidade de números na Requisição */
		int numberAux = TableUtil.charQuantity(numbers, ",") + 1;

		/* Percorre todos os números da requisição */
		for (int i = 0; i < numberAux; i++) {

			/* Utiliza ',' para validar se existe mais de 1 número para ser processado 
			 * Caso contrário processa o número restante*/
			if (numbers.contains(",")) {
				
				/* Valida o index da próxima ','
				 * Caso index 1 o número não é negativo
				 * Caso index 2 o número é negativo - EX:-1, */
				int index = numbers.indexOf(",");
				String num = "0";

				/* Se o index for 2 - realiza o processamento de número negativo
				 * se não realiza o processamento de número positivo */
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
		
		/* Popula matriz a partir de uma lista */
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

				/* Move os números da primeira Linha */
				if (line == 0) {

					listNumbersProcess.add(matriz[line][column]);

				}
				
				/* Move os números da última coluna - Com exceção do número da última linha */
				if (column == lastColumnAndLine && !(line == lastColumnAndLine)) {
					listNumbersProcess.add(matriz[line][lastColumnAndLine]);
				}
				
				/* Move os números da última linha */
				if (line == lastColumnAndLine) {
					if (getLastIndex) {
						listNumbersProcess.add(matriz[line][0]);
						getLastIndex = false;
					}else {
						listNumbersProcess.add(listNumbersProcess.size() - lastIndexCounter, matriz[line][column]);
						lastIndexCounter++;
					}
				}
				
				/* Adiciona os números da última coluna a uma lista Auxiliar */
				if (!(line == 0 && line == lastColumnAndLine) && column == 0 ) {
					listAux.add(matriz[line][0]);
				}
			}

		}
		
		/* Move os números da última coluna a partir da lista Auxiliar*/
		lastIndexCounterAux = 0;
		listAux.forEach(item -> {
			listNumbersProcess.add(listNumbersProcess.size() - lastIndexCounterAux, item);
			lastIndexCounterAux++;
		});
		
		/* MOve o último número para o index 0 - Para rotacionar no sentido horário */
		listNumbersProcess.add(0, listNumbersProcess.get(listNumbersProcess.size() - 1));
		listNumbersProcess.remove(listNumbersProcess.size() - 1);
		
		return listNumbersProcess;
	}

	@Override
	public int[][] moveNumbersInMatriz(List<Integer> newList, int[][] matriz, int primaryListSize) {
		
		int columnAndLineValue = TableUtil.calculateColumnAndLine(primaryListSize);
		int lastColumnAndLine = columnAndLineValue - 1;
		int countLastLine = 0;
		
		
		for (int line = 0; line < columnAndLineValue; line++) {
			
			for (int column = 0; column < columnAndLineValue; column++) {
				
				/* Move na matriz os números da primeira linha */
				if (line == 0) {
					matriz[line][column] = newList.get(0);
					newList.remove(0);
				}
				
				/* Move na matriz os números da última coluna - Com exceção da última linha */
				if (!(line == 0 || line == lastColumnAndLine) && column == lastColumnAndLine) {
					matriz[line][column] = newList.get(0);
					newList.remove(0);
				}
				
				/* Move os números da última linha */
				if (line == lastColumnAndLine) {
					
					int indexToAdd = lastColumnAndLine - countLastLine;
					matriz[line][column] = newList.get(indexToAdd);
					newList.remove(indexToAdd);
					countLastLine++;
				}
							
			}
			
		}
		
		/* Move os números da primeira coluna */
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
