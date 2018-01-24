package com.company.textservice.service;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ashot.karapetyan on 1/22/18.
 */
public class ParallelTextServiceImpl
		implements TextService {

	private final QuotesProvider quotesProvider;
	private ExecutorService executorService;

	public ParallelTextServiceImpl(QuotesProvider quotesProvider){
		this.quotesProvider = quotesProvider;
		this.executorService = Executors.newFixedThreadPool(10);
	}

	@Override
	public QuoteExtractionResult findQuotes(@NotNull String input) {
		Preconditions.checkNotNull(input);
		QuoteExtractionResult result = new QuoteExtractionResult();
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		List<String> sentences = sentenceExtractor.extractSentences(input);
		List<CompletableFuture> futures = new ArrayList<>();
		for(int i = 0; i<sentences.size(); i++){
			final int ii = i;
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
				for (int j = ii + 1; j <= sentences.size(); j++) {
					String subSentence = Joiner.on(" ").join(sentences.subList(ii, j));
					if (this.quotesProvider.containsQuote(subSentence)) {
						result.addQuote(subSentence, this.quotesProvider.getAuthor(subSentence));
					}

				}
			}, this.executorService);
			futures.add(future);

		}

		CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
		return result;
	}
}
