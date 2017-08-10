package com.eking.apollo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApolloApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void mockMvcBuild(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
	public void should_get_reading_list_ok() throws Exception {
	    mockMvc.perform(get("/readingList/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", is(empty())));
	}

    @Test
    public void should_be_able_to_add_book_to_reading_list() throws Exception {
        String bookTitle = "a new book";
        String readerId = "1";
        mockMvc.perform(post("/readingList/" + readerId)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", bookTitle))
                .andExpect(status().is3xxRedirection());

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setTitle(bookTitle);
        expectedBook.setReader(readerId);

        mockMvc.perform(get("/readingList/" + readerId))
                .andExpect(model().attribute(
                        "books",
                        contains(samePropertyValuesAs(expectedBook))));
    }
}
