package com.table.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.table.api.model.dto.EntryNumbersDTO;
import com.table.api.service.TableService;
import com.table.api.service.impl.TableServiceImpl;

@RestController
@RequestMapping(value = "/table/v1")
public class TableController {
	
	private TableService tableService = new TableServiceImpl();
	
	@PostMapping("/process")
	public int[][] tableProcessJson(@RequestBody EntryNumbersDTO entryNumbers) {
		
		int[][] matrizProcess = new int[0][0];
		
		List<Integer> numbersList = tableService.processNumbersToList(entryNumbers.getNumbers().trim());
		int[][] matriz = tableService.processListToMatriz(numbersList);
		
		if (matriz.length > 0) {
			List<Integer> newList = tableService.moveNumbersInNewList(numbersList.size() , matriz);
			matrizProcess = tableService.moveNumbersInMatriz(newList, matriz, numbersList.size());
		}
		
		return matrizProcess;
	}
	
}
