package com.company.quotefinder.service;

import com.company.quotefinder.service.dto.ExtractionResultDto;

import javax.validation.constraints.NotNull;

/**
 * @author ashot.karapetyan on 1/22/18.
 */
public interface QuoteFinder {

	/**
	 * Extract quotes from given text.
	 */
	ExtractionResultDto extractQuotes(@NotNull String input);

}
