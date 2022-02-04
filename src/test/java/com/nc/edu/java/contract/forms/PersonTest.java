package com.nc.edu.java.contract.forms;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {

	@Test
	public void testPersonAge() {
		Person person = new Person(1, "Alex","27.11.1980", "Man", "4212777888");
		int resault = person.getAge();
		assertEquals(41,resault);
	}

}
