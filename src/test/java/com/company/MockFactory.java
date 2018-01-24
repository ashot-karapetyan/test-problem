package com.company;

import com.company.textservice.service.QuoteExtractionResult.QuoteExtractionResultBuilder;
import com.company.textservice.service.TextService;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
public class MockFactory {


//	public TextService getMockedTextService() {
//		TextService textService =  mock(TextService.class);
//		when(textService.findtQuotes(Arrays.asList("badinput"))).
//				thenReturn(new QuoteExtractionResultBuilder().addUnParsedLine("badinput").build());
//		when(textService.findtQuotes(Arrays.asList("good\tinput"))).
//				thenReturn(new QuoteExtractionResultBuilder().addParsedQuote("input").build());
//		return textService;
//
//
//	}
}
