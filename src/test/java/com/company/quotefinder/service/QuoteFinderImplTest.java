package com.company.quotefinder.service;

import com.company.quotefinder.service.provider.QuotesProviderImpl;
import org.assertj.core.util.Strings;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
public class QuoteFinderImplTest {

	private QuoteFinder quoteFinder;

	@Before
	public void before() {
		String quotesPath = System.getProperty("quotes.path");
		if (Strings.isNullOrEmpty(quotesPath)) {
			ClassLoader loader = getClass().getClassLoader();
			quotesPath = loader.getResource("quotes.txt").getPath();
		}
		this.quoteFinder = new QuoteFinderImpl(new QuotesProviderImpl(Paths.get(quotesPath)));
	}

	@Test
	public void testParsedQuotes() {
		assertThat(
				this.quoteFinder.extractQuotes("If you live to be a hundred, I want to live to be a hundred minus one day so I never have to live without you. Promise me you'll always remember: You're braver than you believe, and stronger than you seem, and smarter than you think.").quotes.keySet(),
				contains(
						"If you live to be a hundred, I want to live to be a hundred minus one day so I never have to live without you.",
						"Promise me you'll always remember: You're braver than you believe, and stronger than you seem, and smarter than you think."));
	}

	@Test(expected = NullPointerException.class)
	public void testNullCase() {
		this.quoteFinder.extractQuotes(null);
	}

}
