package com.table.api.service;

import java.util.List;

public interface TableService {

	public List<Integer> processNumbersToList(String trim);

	public int[][] processListToMatriz(List<Integer> numbersList);
	
	public List<Integer> moveNumbersInNewList(int listSize, int[][] matriz);

	public int[][] moveNumbersInMatriz(List<Integer> newList, int[][] matriz, int primaryListSize);

	public int[][] processFullMatrix(int turns, int[][] matrizProcess, int columnAndLine);

}
