package com.ying.tjava.encode;

import java.lang.reflect.Field;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
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
/**
 * 默认Jackson只序列化public的字段(或者可以通过getXXX访问的字段)
 */
@JacksonXmlRootElement(localName = "book")
public class Book {
	@JacksonXmlProperty(isAttribute = true)
	private String id;
	private String name;
	private String author;
	@JacksonXmlProperty(localName = "isbn")
	private Isbn isbn;
	
	/**
	 *  <tags>
    		<tag>story</tag>
    		<tag>fantasy</tag>
  		</tags>
	 */
	@JacksonXmlElementWrapper(localName = "tags")
    @JacksonXmlProperty(localName = "tag")
	private List<String> tags;
	private String pubDate;
	
	public void printf() throws IllegalArgumentException, IllegalAccessException {
		Class m = getClass();
		for (Field f : m.getDeclaredFields()) {
			f.setAccessible(true);
			
			if (f.toString().contains("Isbn")) {
				Isbn isbn = (Isbn) f.get(this);
				System.out.printf("lang:%s value: %s\n", isbn.getLang(), isbn.getValue());
				continue;
			}
			
			System.out.printf("%s:%s\n", f, f.get(this));
		}
	}
	
	public Book() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public Isbn getIsbn() {
		return isbn;
	}


	public void setIsbn(Isbn isbn) {
		this.isbn = isbn;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	public String getPubDate() {
		return pubDate;
	}


	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}


	public class Isbn {
		//	将lang属性注入
		@JacksonXmlProperty(isAttribute = true)
		private String lang;
		//	将TEXT文本注入
		@JacksonXmlText
		private String value;

		public Isbn() {
		}

		public String getLang() {
			return lang;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	
}
