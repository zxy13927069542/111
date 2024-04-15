package com.ying.tjava.junit;


import org.junit.jupiter.api.*;


import org.junit.Test;

public class TestJunit {

	@Test
	public void exceptionTesting() {
	    Assertions.assertThrows(
	    		IllegalArgumentException.class,
	           () -> {
	        	   throw new IllegalArgumentException();
	           }
	    );

	}
}
