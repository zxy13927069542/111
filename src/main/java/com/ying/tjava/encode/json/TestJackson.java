package com.ying.tjava.encode.json;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ying.tjava.encode.Book;
import com.ying.tjava.encode.JsonBook;
import com.ying.tjava.encode.JsonBook.Author;
import com.ying.tjava.encode.Book.Isbn;

/**
 * {
    "id": 1,
    "name": "Java核心技术",
    "author": {
        "firstName": "Abc",
        "lastName": "Xyz"
    },
    "isbn": "9542-123-366-4567",
    "tags": ["Java", "Network"],
    "pubDate": "2024-03-06"
}
 */
public class TestJackson {
	@Test
	public void deserialize() throws StreamReadException, DatabindException, IOException, IllegalArgumentException, IllegalAccessException {
		//	1、导入依赖：com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.0
		//	2、注册JavaTimeModule
		//	支持将日期转换成LocalDate
		ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());
		JsonBook book = om.readValue(getClass().getResourceAsStream("/test.json"), JsonBook.class);
		book.printf();
		
	}
	
	@Test
	public void serialize() throws JsonProcessingException {
		JsonBook book = new JsonBook();
		book.setId(369);
		book.setName("哈利波特");
		Author a = book.new Author(); 
		a.setFirstName("大卫");
		a.setLastName("史密斯");
		book.setAuthor(a);
		book.setIsbn(new BigDecimal("95421233664567"));
		book.setTags(List.of("story", "fantasy"));
		book.setPubDate(LocalDate.of(2024, 04, 06));	
		
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
		//	加缩进
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String prettyJson =  mapper.writeValueAsString(book);
		System.out.println(prettyJson);
	}
}
