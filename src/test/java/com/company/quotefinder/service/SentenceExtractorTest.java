package com.company.quotefinder.service;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * @author ashot.karapetyan on 1/24/18.
 */
public class SentenceExtractorTest {

	@Test
	public void testDot() {
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		assertThat(sentenceExtractor.extractSentences("Test."), contains("Test."));
	}

	@Test
	public void testExtractSymbol() {
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		assertThat(sentenceExtractor.extractSentences("Test. No. Yes!"), contains("Test.", "No.", "Yes!"));
	}

	@Test
	public void testQuotes() {
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		assertThat(sentenceExtractor.extractSentences("'Oh, I don't know.' Test. No. Yes!"),
				contains("'Oh, I don't know.'", "Test.", "No.", "Yes!"));
	}

	@Test
	public void testQuestionMark() {
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		assertThat(sentenceExtractor.extractSentences("Test? No? Yes!"),
				contains("Test?", "No?", "Yes!"));
	}

}
