package com.company.quotefinder.endpoint;

import com.company.quotefinder.service.QuoteFinder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ashot.karapetyan on 1/23/18.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ImportResource("classpath:test-controllers-config.xml")
@DirtiesContext
public class TextControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private QuoteFinder quoteFinder;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testUnparsed()
			throws Exception {

		this.mockMvc.perform(post("/").content("badinput").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("quotes").isEmpty());

		Mockito.verify(quoteFinder, times(1)).extractQuotes("badinput");
	}

	@Test
	public void testParsed()
			throws Exception {
		this.mockMvc.perform(post("/").content("Imagination is more important than knowledge.").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("quotes", Matchers.hasKey("Imagination is more important than knowledge.")));

		Mockito.verify(quoteFinder, times(1)).extractQuotes("Imagination is more important than knowledge.");
	}
}
