package com.company.textservice.service.parser;

import com.google.common.base.Preconditions;

import javax.validation.constraints.NotNull;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
public class TabBasedParserStrategy
		implements ParserStrategy {

	@Override
	public boolean canParse(@NotNull String line) {
		Preconditions.checkNotNull(line);
		return line.indexOf("\t") > 0;
	}

	@Override
	public String parse(@NotNull String line) {
		Preconditions.checkNotNull(line);
		return line.substring(line.indexOf("\t") + 1);
	}
}
