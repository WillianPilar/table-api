package com.table.api.service;

import java.util.List;

public interface TableService {

	public List<Integer> processNumbersToList(String trim);

	public int[][] processListToMatriz(List<Integer> numbersList);

}
