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
import com.table.api.util.TableUtil;

@RestController
@RequestMapping(value = "/table/v1")
public class TableController {

	private TableService tableService = new TableServiceImpl();

	@PostMapping("/process")
	public ResponseEntity<int[][]> tableProcessJson(@RequestBody EntryNumbersDTO entryNumbers) {

		int[][] matrizProcess = new int[0][0];

		try {
			
			/* Processa os números da requisição em uma lista*/
			List<Integer> numbersList = tableService.processNumbersToList(entryNumbers.getNumbers().trim());		
			
			/* Cria uma Matriz a partir da lista */
			int[][] matriz = tableService.processListToMatriz(numbersList);
			
			/* Se a lista for maior que 0 - COntinua o processamento para Matriz
			 * Se não retorna 400 (Bad Request) e a lista vázia no Body*/
			if (matriz.length > 0) {
				
				/* Cria uma lista auxiliar com os números em ordem para rotacionar a Matriz */
				List<Integer> newList = tableService.moveNumbersInNewList(numbersList.size(), matriz);
				
				/* Rotaciona as bordas da Matriz no sentido horário */
				matrizProcess = tableService.moveNumbersInMatriz(newList, matriz, numbersList.size());
				
				/* Quantidade de turnos que precisam ser executados */
				int turns = (TableUtil.calculateColumnAndLine(numbersList.size()) / 2) - 1;
				
				/* Caso reste 1 turno ou mais, rotaciona os valores internos da Matriz */
				if (turns >= 1) {
					
					/* Rotaciona valorez internos da Matriz */
					matrizProcess = tableService.processFullMatrix(turns, matrizProcess, TableUtil.calculateColumnAndLine(numbersList.size()));
				}
			
			} else {
				return new ResponseEntity<int[][]>(matrizProcess, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<int[][]>(matrizProcess, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(matrizProcess);
	}

}
