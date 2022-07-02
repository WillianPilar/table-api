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
	
	@PostMapping("/process-json")
	public String tableProcessJson(@RequestBody EntryNumbersDTO entryNumbers) {
		
		List<Integer> numbersList = tableService.processNumbersToList(entryNumbers.getNumbers().trim());
		int[][] matriz = tableService.processListToMatriz(numbersList);
		
		if (matriz.length > 0) {
			int[][] matrizProcess = tableService.moveNumbersInMatriz(numbersList, matriz);
		}
		
		return null;
	}
	
	@PostMapping("/process-text")
	public String tableProcessText(@RequestBody String entryNumbers) {
		
		List<Integer> numbersList = tableService.processNumbersToList(entryNumbers.trim());
		int[][] matriz = tableService.processListToMatriz(numbersList);
		
		return null;
	}

}
