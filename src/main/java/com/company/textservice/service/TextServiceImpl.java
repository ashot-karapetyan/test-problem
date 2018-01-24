package com.company.textservice.service;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ashot.karapetyan on 1/22/18.
 */
public class TextServiceImpl
		implements TextService {

	private final QuotesProvider quotesProvider;

	public TextServiceImpl(QuotesProvider quotesProvider) {
		this.quotesProvider = quotesProvider;
	}

	@Override
	public QuoteExtractionResult findQuotes(@NotNull String input) {
		Preconditions.checkNotNull(input);
		QuoteExtractionResult result = new QuoteExtractionResult();
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		List<String> sentences = sentenceExtractor.extractSentences(input);
		for (int i = 0; i < sentences.size(); i++) {
			for (int j = i + 1; j <= sentences.size(); j++) {
				String subSentence = Joiner.on(" ").join(sentences.subList(i, j));
				if (this.quotesProvider.containsQuote(subSentence)) {
					result.addQuote(subSentence, this.quotesProvider.getAuthor(subSentence));
				}

			}

		}

		return result;
	}
}
