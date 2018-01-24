package com.company.textservice.service;

import com.company.textservice.service.parser.TabBasedParserStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


/**
 * @author ashot.karapetyan on 1/23/18.
 */
public class TextServiceImplTest {

	private TextService textService;
//	@Before
//	public void before(){
//		this.textService = new TextServiceImpl(new TabBasedParserStrategy());
//	}


//	@Test
//	public void testParsedQuotes(){
//		assertThat(this.textService.findQuotes(Arrays.asList("A. A. Milne\tIf you live to be a hundred, I want to live to be a hundred minus one day so I never have to live without you.",
//		"A. A. Milne\tPromise me you'll always remember: You're braver than you believe, and stronger than you seem, and smarter than you think.")).parsedQuotes,
//				contains("If you live to be a hundred, I want to live to be a hundred minus one day so I never have to live without you.","Promise me you'll always remember: You're braver than you believe, and stronger than you seem, and smarter than you think."));
//	}
//
//	@Test
//	public void testEmptyUnParsedQuotes(){
//		assertThat(this.textService.findtQuotes(Arrays.asList("A. A. Milne\tIf you live to be a hundred, I want to live to be a hundred minus one day so I never have to live without you.",
//				"A. A. Milne\tPromise me you'll always remember: You're braver than you believe, and stronger than you seem, and smarter than you think.")).unParsedLines,
//				emptyCollectionOf(String.class));
//	}
//
//	@Test
//	public void testUnParsedQuotes(){
//		assertThat(this.textService.findtQuotes(Arrays.asList("A. A. Milne\tIf you live to be a hundred, I want to live to be a hundred minus one day so I never have to live without you.",
//				"A. A. Milne Promise me you'll always remember: You're braver than you believe, and stronger than you seem, and smarter than you think.")).unParsedLines,
//				contains("A. A. Milne Promise me you'll always remember: You're braver than you believe, and stronger than you seem, and smarter than you think."));
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void testNullCase(){
//		this.textService.findtQuotes(null);
//	}

}