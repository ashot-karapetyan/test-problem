package com.company.textservice.service;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * @author ashot.karapetyan on 1/22/18.
 */
public class TextServiceImpl2
		implements TextService {

	private final QuotesProvider quotesProvider;

	public TextServiceImpl2(QuotesProvider quotesProvider) {
		this.quotesProvider = quotesProvider;
	}

	@Override
	public QuoteExtractionResult findQuotes(@NotNull String input) {
		Preconditions.checkNotNull(input);
		QuoteExtractionResult result = new QuoteExtractionResult();
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		List<String> sentences = sentenceExtractor.extractSentences(input);
		for (int i = 0; i < sentences.size(); i++) {
			String firstSentence = sentences.get(i);
			Collection<Integer> lengths = this.quotesProvider.getQuotesStartsWith(firstSentence);
			final int finalI = i;
			lengths.forEach(j -> {
				if (finalI + j <= sentences.size()) {
					String subSentence = Joiner.on(" ").join(sentences.subList(finalI, j + finalI));
					String author = this.quotesProvider.getAuthor(subSentence);
					if (author != null) {
						result.addQuote(subSentence, author);
					}
				}

			});

		}

		return result;
	}
}
