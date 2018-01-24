package com.company.textservice.config;

import com.company.textservice.service.*;
import com.company.textservice.service.parser.ParserStrategy;
import com.company.textservice.service.parser.TabBasedParserStrategy;
import org.springframework.context.annotation.Bean;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public ParserStrategy parserStrategy(){
		return new TabBasedParserStrategy();
	}


	public QuotesProvider quotesProvider(){
		return new QuotesProvider();
	}

	@Bean
	public TextService textService(){
		return new TextServiceImpl2(quotesProvider());
	}



}
