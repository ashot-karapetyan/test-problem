package com.company.textservice.service.parser;

import javax.validation.constraints.NotNull;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
public interface ParserStrategy {

	boolean canParse(@NotNull String line);

	String parse(@NotNull String line);
}
