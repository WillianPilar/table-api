package com.table.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<int[][]> tableProcessJson(@RequestBody EntryNumbersDTO entryNumbers) {

		int[][] matrizProcess = new int[0][0];

		try {

			List<Integer> numbersList = tableService.processNumbersToList(entryNumbers.getNumbers().trim());
			int[][] matriz = tableService.processListToMatriz(numbersList);

			if (matriz.length > 0) {
				List<Integer> newList = tableService.moveNumbersInNewList(numbersList.size(), matriz);
				matrizProcess = tableService.moveNumbersInMatriz(newList, matriz, numbersList.size());
			} else {
				return new ResponseEntity<int[][]>(matrizProcess, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<int[][]>(matrizProcess, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(matrizProcess);
	}

}
