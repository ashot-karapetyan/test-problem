package com.company.quotefinder.config;

import com.company.quotefinder.service.*;
import com.company.quotefinder.service.provider.QuotesProvider;
import com.company.quotefinder.service.provider.QuotesProviderImpl;
import com.google.common.base.Strings;
import org.springframework.context.annotation.Bean;

import java.nio.file.Paths;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

	public QuotesProvider quotesProvider(){
		String quotesPath = System.getProperty("quotes.path");
		if(Strings.isNullOrEmpty(quotesPath)){
			ClassLoader loader = getClass().getClassLoader();
			quotesPath = loader.getResource("quotes.txt").getPath();
		}
		return new QuotesProviderImpl(Paths.get(quotesPath));
	}

	@Bean
	public QuoteFinder quoteFinder(){
		return new QuoteFinderImpl(quotesProvider());
	}



}
