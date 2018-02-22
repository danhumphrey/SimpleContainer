package com.github.danhumphrey.ioc;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSimpleContainer {

	SimpleContainer container;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		container = new SimpleContainer();
		container.removeAll();
	}

	@Test
	public void testHasEntryMethodReturnsFalseWhenNothingRegisteredInContainer() {
		Assert.assertFalse("Expecting hasEntry to return false when nothing has been registered", container.hasEntry(String.class));
	}
	
	@Test
	public void testHasEntryMethodReturnsTrueWhenMatchingEntryRegisteredInContainer() {
		container.register("This is a String");
		Assert.assertTrue("Expecting hasEntry to return true when matching entry has been registered", container.hasEntry(String.class));
	}

	@Test
	public void testHasNamedEntryMethodReturnsFalseWhenNothingRegisteredInContainer() {
		Assert.assertFalse("Expecting hasNamedEntry to return false when nothing has been registered", container.hasNamedEntry("someRandomName"));
	}
	
	@Test
	public void testHasNamedEntryMethodReturnsFalseWhenEntryRegisteredWithDifferentName() {
		container.register("someDifferentName", "This is a String");
		Assert.assertFalse("Expecting hasNamedEntry to return false when entry has been registered with a different name", container.hasNamedEntry("someRandomName"));
	}
	
	@Test
	public void testHasNamedEntryMethodReturnsTrueWhenEntryRegisteredWithMatchingName() {
		container.register("someRandomName", "This is a String");
		Assert.assertTrue("Expecting hasNamedEntry to return false when entry has been registered with a different name", container.hasNamedEntry("someRandomName"));
	}
}
