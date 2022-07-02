package com.table.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class EntryNumbersDTO {

	@JsonProperty("numbers")
	public String numbers;
	
}
