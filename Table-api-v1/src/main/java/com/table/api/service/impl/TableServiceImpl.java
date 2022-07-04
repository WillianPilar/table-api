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
				 * Captura o numéro antes da ',' 
				 * Remove da lista de números da entrada
				 * Adiciona na lista de números para processamento
				 * */
				int index = numbers.indexOf(",");
				String num = numbers.substring(0, index);
				numbers = numbers.replaceFirst(numbers.substring(0, index + 1), "").trim();
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
				if (column == lastColumnAndLine && !(line == 0 || line == lastColumnAndLine)) {
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
				if (!(line == 0 || line == lastColumnAndLine) && column == 0 ) {
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

	@Override
	public int[][] processFullMatrix(int turns, int[][] matrizProcess, int columnAndLine) {
		
		int lastColumnAndLine = columnAndLine - 1;
		List<Integer> listNumbersProcess = new ArrayList<>();
		int[][] matriz;
		List<Integer> newList;
		Boolean isPrimary = true;
		
		
		for (int turn = 0; turn < turns; turn++) {
			
			if (isPrimary) {
				for (int line = 1; line < columnAndLine; line++) {
					
					for (int column = 1; column < columnAndLine; column++) {
						
						if (!(line == 0 || line == lastColumnAndLine) && !(column == 0 || column == lastColumnAndLine)) {
							
							listNumbersProcess.add(matrizProcess[line][column]);
							
						}
						
					}
					
				}
				isPrimary = false;
			}
			matriz = this.processListToMatriz(listNumbersProcess);
			newList = this.moveNumbersInNewList(listNumbersProcess.size(), matriz);
			matriz = this.moveNumbersInMatriz(newList, matriz, listNumbersProcess.size());
			matrizProcess = this.updateMatriz(matrizProcess, matriz, turn + 1, columnAndLine - 1);
			
		}
		
		
		
		return matrizProcess;
	}

	private int[][] updateMatriz(int[][] matriz, int[][] matrizAux, int index, int lastColumnAndLine) {
		
		for (int line = index; line < lastColumnAndLine ; line++) {
			
			for (int column = index; column < lastColumnAndLine; column++) {
				
				if((line >= index && line <= lastColumnAndLine - index) && (column >= index && column <= lastColumnAndLine - index )) {
					
					if ((line == index || line == lastColumnAndLine - index ) || (column == index || column == lastColumnAndLine - index )) {
						
						int test = matrizAux[line - 1][column - 1];
						matriz[line][column] = matrizAux[line - 1][column - 1];
					}
				}
				
			}
			
		}
		
		return matriz;
	}
}
