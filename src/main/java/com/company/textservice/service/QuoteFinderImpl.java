package com.company.textservice.service;

import com.company.textservice.service.dto.ExtractionResultDto;
import com.company.textservice.service.dto.ExtractionResultDtoBuilder;
import com.company.textservice.service.provider.QuotesProvider;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author ashot.karapetyan on 1/22/18.
 */
public class QuoteFinderImpl
		implements QuoteFinder {

	private final QuotesProvider quotesProvider;

	public QuoteFinderImpl(QuotesProvider quotesProvider) {
		this.quotesProvider = quotesProvider;
	}

	@Override
	public ExtractionResultDto extractQuotes(@NotNull String input) {
		Preconditions.checkNotNull(input);
		ExtractionResultDtoBuilder dtoBuilder = new ExtractionResultDtoBuilder();
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		List<String> sentences = sentenceExtractor.extractSentences(input);
		for (int i = 0; i < sentences.size(); i++) {
			String startSentence = sentences.get(i);
			Collection<Integer> lengths = this.quotesProvider.getQuotesStartsWith(startSentence);
			final int finalI = i;
			lengths.forEach(j -> {
				if (finalI + j <= sentences.size()) {
					String subSentence = Joiner.on(" ").join(sentences.subList(finalI, j + finalI));
					Optional<String> author = this.quotesProvider.getAuthor(subSentence);
					author.ifPresent((aut) -> dtoBuilder.addQuote(subSentence, aut));
				}
			});
		}
		return dtoBuilder.build();
	}
}
