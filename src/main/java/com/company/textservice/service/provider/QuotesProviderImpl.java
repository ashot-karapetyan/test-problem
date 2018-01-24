package com.company.textservice.service.provider;

import com.company.textservice.service.SentenceExtractor;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
*  Provides quote database.
 * @author ashot.karapetyan on 1/23/18.
 */
public class QuotesProviderImpl implements QuotesProvider{

	private static Logger logger = LogManager.getLogger(QuotesProviderImpl.class);
	private final Path quotesPath;
	/*
		maps quote with author
	 */
	private Map<String, String> quotes;

	/*
		maps the sentence and the length(count sentences) of quotes starts with that sentence.
	 */
	private Multimap<String, Integer> quotesByPrefix;

	/**
	 * Load quotes from given path
	 */
	public QuotesProviderImpl(Path quotesPath) {
		this.quotesPath = quotesPath;
		loadQuotes();
	}


	@Override
	public Optional<String> getAuthor(String quote) {
		return Optional.of(quotes.get(quote));
	}

	@Override
	public Collection<Integer> getQuotesStartsWith(String sentence) {
		return quotesByPrefix.get(sentence);
	}

	private void loadQuotes() {

		//take 1024 as initial capacity, to minimize collision
		this.quotes = new HashMap<>(1024);
		this.quotesByPrefix = HashMultimap.create(30000, 3);
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		try {
			List<String> strings = Files.readAllLines(this.quotesPath);
			strings.forEach(s -> {
				String[] parts = s.split("\t");
				quotes.put(parts[1], parts[0]);
				List<String> sentences = sentenceExtractor.extractSentences(parts[1]);
				quotesByPrefix.put(sentences.get(0), sentences.size());
			});

			logger.info("Loaded {} quotes.", quotes.size());
		}
		catch (IOException e) {
			logger.error(e);
		}
	}

}
