package com.company.textservice.service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
public class QuotesProvider {

	private static Logger logger = LogManager.getLogger(QuotesProvider.class);
	private Map<String, String> quotes;
	private Multimap<String, Integer> quotesByPrefix;

	public QuotesProvider() {
		loadQuotes();
	}

	public void loadQuotes() {

		this.quotes = new HashMap<>(1024);
		this.quotesByPrefix = HashMultimap.create(30000, 3);
		SentenceExtractor sentenceExtractor = new SentenceExtractor();
		try {
			List<String> strings = Files.readAllLines(Paths.get("/Users/ashot.karapetyan/Downloads/quotes.txt"));
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

	public boolean containsQuote(String toCheck) {
		return quotes.containsKey(toCheck);
	}

	public String getAuthor(String quote) {
		return quotes.get(quote);
	}

	public Collection<Integer> getQuotesStartsWith(String sentence){
		return quotesByPrefix.get(sentence);
	}
}
