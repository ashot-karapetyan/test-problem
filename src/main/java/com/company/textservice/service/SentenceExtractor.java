package com.company.textservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
*
 * @author ashot.karapetyan on 1/24/18.
 */
public class SentenceExtractor {

	/**
	 * Extract sentences from line.
	 * Ending symbols for sentence are: <pre><b>. ? ! .'</b></pre>
	 * @return sentences with ending symbol.
	 */
	public List<String> extractSentences(String line){
		// 1. split by .'
		// 2. split by ? ! .
		// split by (?<=%c%) to keep %c% character in split parts.
		return Arrays.stream(line.split("(?<=\\.')")).
			flatMap(s->Arrays.stream(s.split("(?<=[?!.]\\s)"))).map(String::trim).collect(Collectors.toList());
	}
}
