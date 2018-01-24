package com.company.textservice.service;

import java.util.List;

/**
 * @author ashot.karapetyan on 1/22/18.
 */
public interface TextService {

	QuoteExtractionResult findQuotes(String input);

}
