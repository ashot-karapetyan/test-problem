package com.company;

import com.company.textservice.service.QuoteFinder;
import com.company.textservice.service.dto.ExtractionResultDtoBuilder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
public class MockFactory {

	public QuoteFinder getMockedTextService() {
		QuoteFinder textService = mock(QuoteFinder.class);
		when(textService.extractQuotes("badinput")).thenReturn(new ExtractionResultDtoBuilder().build());
		when(textService.extractQuotes("Imagination is more important than knowledge.")).thenReturn(
				new ExtractionResultDtoBuilder().addQuote("Imagination is more important than knowledge.", "A Einstein").build());
		return textService;

	}
}
