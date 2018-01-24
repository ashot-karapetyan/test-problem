package com.company.textservice.service.dto;

import com.google.common.collect.ImmutableMap;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Holds find quotes.
 * @author ashot.karapetyan on 1/23/18.
 */
public class ExtractionResultDto {

	public final Map<String, String> quotes;

	ExtractionResultDto(Map<String, String> quotes) {
		this.quotes = ImmutableMap.copyOf(quotes);
	}

}
