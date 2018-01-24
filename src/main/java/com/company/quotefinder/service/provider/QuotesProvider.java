package com.company.quotefinder.service.provider;

import java.util.Collection;
import java.util.Optional;

/**
 * Provides quote database.
 * 
 * @author ashot.karapetyan on 1/23/18.
 */
public interface QuotesProvider {

	/**
	 * Returns author of quote.
	 * @return Optional.EMPTY if quote doesn't exist.
	 */
	Optional<String> getAuthor(String quote);

	/**
	 * Returns the count of sentences in quote, which starts with given sentence.
	 * For example
	 * If we have following quotes database:
	 *  SA.SB.SC
	 *  SB.
	 *  SA
	 *
	 * for given sentence = SA
	 * result will be [3,1]
	 *
	 */
	Collection<Integer> getQuotesStartsWith(String sentence);

}
