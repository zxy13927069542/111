package com.ying.tjava.encode.xml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.ying.tjava.encode.Book;
import com.ying.tjava.encode.Book.Isbn;

/**
 * <book id="1">
    <name>Java核心技术</name>
    <author>Cay S. Horstmann</author>
    <isbn lang="CN">1234567</isbn>
    <tags>
        <tag>Java</tag>
        <tag>Network</tag>
    </tags>
    <pubDate/>
</book>
 */
public class TestJackson {
	
	@Test
	public void deserialize() throws StreamReadException, DatabindException, IOException, IllegalArgumentException, IllegalAccessException {
		ObjectMapper xmlMapper = new XmlMapper();
		Book b = xmlMapper.readValue(getClass().getResourceAsStream("/hello.xml"), Book.class);
		Class m = Book.class;
		for (Field f : m.getDeclaredFields()) {
			f.setAccessible(true);
			
			if (f.toString().contains("Isbn")) {
				Isbn isbn = (Isbn) f.get(b);
				System.out.printf("lang:%s value: %s\n", isbn.getLang(), isbn.getValue());
				continue;
			}
			
			System.out.printf("%s:%s\n", f, f.get(b));
		}
	}
	
	@Test
	public void serialize() throws StreamWriteException, DatabindException, IOException, URISyntaxException {
		Book book = new Book();
		book.setId("book-369");
		book.setName("哈利波特");
		book.setAuthor("大卫史密斯11");
		Isbn isbn = book.new Isbn();
		isbn.setLang("zh_CN");
		isbn.setValue("95426753");
		book.setIsbn(isbn);
		book.setTags(List.of("story", "fantasy"));
		book.setPubDate("2022-06-09");	
		
		ObjectMapper xmlMapper = new XmlMapper();
		//	加缩进
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		File file = new File("./src/main/resources/hello1.xml");
		xmlMapper.writeValue(file, book);
	}
	
}


