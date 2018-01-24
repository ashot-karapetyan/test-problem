package com.company.textservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ashot.karapetyan on 1/24/18.
 */
public class SentenceExtractor {

	public List<String> extractSentences(String line){
		return Arrays.stream(line.split("(?<=\\.')")).
			flatMap(s->Arrays.stream(s.split("(?<=[?!.]\\s)"))).map(String::trim).collect(Collectors.toList());
	}
}
