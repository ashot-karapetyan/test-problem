package com.company.textservice.service.parser;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
public class TabBasedParserStrategyTest {

	private TabBasedParserStrategy tabBasedParserStrategy;


	@Before
	public void before(){
		this.tabBasedParserStrategy = new TabBasedParserStrategy();
	}



	@Test
	public void testNotParseable(){
		assertThat(this.tabBasedParserStrategy.canParse("Stringwithouttabs"), equalTo(false));
	}

	@Test
	public void testNotParseableWithSpace(){
		assertThat(this.tabBasedParserStrategy.canParse("String withouttabs"), equalTo(false));
	}

	@Test
	public void testCanParse(){
		assertThat(this.tabBasedParserStrategy.canParse("String\twithtabs"), equalTo(true));
	}

	@Test(expected = NullPointerException.class)
	public void testCanParseNullCase(){
		this.tabBasedParserStrategy.canParse(null);
	}


	@Test
	public void testParse(){
		assertThat(this.tabBasedParserStrategy.parse("fname lname\tquote"), equalTo("quote"));
	}

	@Test(expected = NullPointerException.class)
	public void testParseNullCase(){
		this.tabBasedParserStrategy.parse(null);
	}
}