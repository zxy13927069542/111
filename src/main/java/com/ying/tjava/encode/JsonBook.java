package com.ying.tjava.encode;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;


/**
 * 默认Jackson只序列化public的字段(或者可以通过getXXX访问的字段)
 *
 */
public class JsonBook {
	private int id;
	private String name;
	private Author author;

	/**
	 * @JsonDeserialize(using = IsbnDeserialize.class) 自定义字段反序列化解析
	 * @JsonFormat(shape = JsonFormat.Shape.STRING) 输出为字符串
	 */
	@JsonDeserialize(using = IsbnDeserialize.class)
	@JsonSerialize(using = IsbnSerialize.class)
	private BigDecimal isbn;
	
	private List<String> tags;
	
	//	private String pubDate;
	// 通过@JsonFormat注解可以自定义输出格式
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate pubDate;
	
	public void printf() throws IllegalArgumentException, IllegalAccessException {
		Class m = getClass();
		for (Field f : m.getDeclaredFields()) {
			f.setAccessible(true);
			
			if (f.toString().contains("Author")) {
				Author author = (Author) f.get(this);
				System.out.printf("Author.firstName = %s\n", author.firstName);
				System.out.printf("Author.lastName = %s\n", author.lastName);
				continue;
			}
			
			System.out.printf("%s:%s\n", f, f.get(this));
		}
	}
	
	public JsonBook() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public BigDecimal getIsbn() {
		return isbn;
	}

	public void setIsbn(BigDecimal isbn) {
		this.isbn = isbn;
	}

	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	public LocalDate getPubDate() {
		return pubDate;
	}


	public void setPubDate(LocalDate pubDate) {
		this.pubDate = pubDate;
	}

	public class Author {
		private String firstName;
		private String lastName;
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Author() {
		}
		
	}
}

/**
 * 自定义字段反序列器
 */
class IsbnDeserialize extends JsonDeserializer<BigDecimal> {

	@Override
	public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String value = p.getValueAsString();
		if (value == null) return null;
		try {
			return new BigDecimal(value.replace("-", ""));			
		} catch (NumberFormatException e) {
			throw new JsonParseException(p, value, e);
		}
	}
	
}

class IsbnSerialize extends JsonSerializer<BigDecimal> {
	private static final int GROUP_SIZE = 4;
	private static final char GROUP_SEPARATOR = '-';

	@Override
	public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		String isbnString = value.toPlainString();
		// 添加分隔符
        StringBuilder sb = new StringBuilder(isbnString.length() + (isbnString.length() / GROUP_SIZE));
        int i = 0;
        while (i < isbnString.length()) {
            sb.append(isbnString.charAt(i));
            if ((i + 1) % GROUP_SIZE == 0) {
                sb.append(GROUP_SEPARATOR);
            }
            i++;
        }
		gen.writeString(sb.toString());
	}
	
}


