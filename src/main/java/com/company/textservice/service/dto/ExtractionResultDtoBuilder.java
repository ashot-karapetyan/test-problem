package com.company.textservice.service.dto;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Holds find quotes.
 * @author ashot.karapetyan on 1/23/18.
 */
public class ExtractionResultDtoBuilder {

	private final Map<String, String> quotes;

	public ExtractionResultDtoBuilder() {
		this.quotes = new LinkedHashMap<>();
	}

	public ExtractionResultDtoBuilder addQuote(@NotNull String quote, @NotNull String author) {
		this.quotes.put(quote, author);
		return this;
	}

	public ExtractionResultDto build(){
		return new ExtractionResultDto(this.quotes);
	}

}
