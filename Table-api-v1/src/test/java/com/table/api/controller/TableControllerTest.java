package com.table.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.table.api.model.dto.EntryNumbersDTO;

class TableControllerTest {
	
	TableController controller = new TableController();
	
	EntryNumbersDTO numbersInput = new EntryNumbersDTO();
	
	
	
	@BeforeEach
	public void SetUp() {
		numbersInput.setNumbers("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16");
	}

	@Test
	void tableProcessJsonTestSuccess() {
		
		int[][] matrizReturn = {{5, 1, 2, 3}, {9, 10, 6, 4}, {13, 11, 7, 8}, {14, 15, 16, 12}};
		
		ResponseEntity<int[][]> returnTest = controller.tableProcessJson(numbersInput);
		assertThat(returnTest.getBody()).isEqualTo(matrizReturn);
		
	}
	
	@Test
	void tableProcessJsonTestError() {
		
		numbersInput.setNumbers("1, 2");
		int[][] matrizReturn = {};
		
		ResponseEntity<int[][]> returnTest = controller.tableProcessJson(numbersInput);
		assertThat(returnTest.getBody()).isEqualTo(matrizReturn);
		
	}

}
