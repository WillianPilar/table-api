package com.table.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryNumbersDTO {

	@JsonProperty("numbers")
	public String numbers;
	
}
