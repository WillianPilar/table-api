package com.table.api.service;

import java.util.List;

public interface TableService {

	public List<Integer> processNumbersToList(String trim);

	public int[][] processListToMatriz(List<Integer> numbersList);
	
	public int[][] popularMatriz(int[][] matriz, int columnAndLineValue, List<Integer> numbersList);

	public int[][] moveNumbersInMatriz(List<Integer> numbersList, int[][] matriz);

}
