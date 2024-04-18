package com.ying.tjava.junit;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestJunit {

	@Test
	public void exceptionTesting() {
		
		assertThrows(
	    		IllegalArgumentException.class,
	           () -> {
	        	   throw new IllegalArgumentException();
	           }
	    );

	}
}
