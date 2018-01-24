package com.company.textservice.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
public class QuoteExtractionResult {

	public final Map<String, String> quotes;

	public QuoteExtractionResult() {
		this.quotes = new LinkedHashMap<>();
	}

	public void addQuote(@NotNull String quote, @NotNull String author){
		this.quotes.put(quote, author);
	}




}
