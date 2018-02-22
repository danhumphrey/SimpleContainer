package com.github.danhumphrey.ioc;

import static org.junit.Assert.fail;

import javax.swing.Icon;

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
	
	@Test
	public void testRegisterOverwritesAnExistingEntry() {
		container.register("Dan");
		Assert.assertEquals("Expecting resolved entry to equal Dan", "Dan", container.resolve(String.class));
		container.register("Humphrey");
		Assert.assertEquals("Expecting resolved entry to equal Humphrey", "Humphrey", container.resolve(String.class));
	}
	
	
	@Test
	public void testResolveByType() {
		container.register(15);
		Assert.assertEquals("Expecting resolved entry to equal 15", (Integer)15, (Integer)container.resolve(Integer.class));
	}

	
	@Test
	public void testRegisterAndResolveByName() {
		container.register("forename", "Dan");
		container.register("age", 41);
		
		Assert.assertEquals("Expecting resolved entry to equal Dan", "Dan", container.resolve("forename"));
		Assert.assertEquals("Expecting resolved entry to equal 41", (Integer)41, container.resolve("age"));
	}
	
	@Test
	public void testResolveByComplexType() {
		container.register(new MyComplexClass("Dan"));
		Assert.assertEquals("Expecting complex type to be resolved and usable", "My name is Dan", container.resolve(MyComplexClass.class).toString());
	}
	
	@Test
	public void testResolveComplexTypeByName() {
		container.register("forename greeter", new MyComplexClass("Dan"));
		container.register("surname greeter", new MyComplexClass("Humphrey"));
		Assert.assertEquals("Expecting complex type to be resolved and usable", "My name is Dan", container.resolve("forename greeter").toString());
		Assert.assertEquals("Expecting complex type to be resolved and usable", "My name is Humphrey", container.resolve("surname greeter").toString());
	}
	
	@Test
	public void testResolveByNameWithNoMatchingEntryReturnsNull() {
		Object a  = container.resolve("Uh oh");
		Assert.assertNull("Expecting resolve to return null when matching entry is not found", a);
	}
	
	@Test
	public void testRemoveEntryByType() {
		container.register(new MyComplexClass("Dan"));
		container.remove(MyComplexClass.class);
		Assert.assertNull("Expecting resolve to return null after entry removed", container.resolve(MyComplexClass.class));
	} 

	@Test
	public void testRemoveEntryByName() {
		container.register("forename", "Dan");
		container.remove("forename");
		Assert.assertNull("Expecting resolve to return null after entry removed", container.resolve("forename"));
	}
	
	@Test 
	public void testRegisterAsTypeThrowsIllegalArgumentExceptionWhenEntryDoesNotExtendOrImplementType() {
		try {
			container.registerAs(new MyComplexClass("Dan"), Icon.class);
		}catch(IllegalArgumentException ex) {
			return;
		}
		fail("IllegalArgumentException not thrown when expected");
	}

	@Test 
	public void testHasEntryAfterRegisterAsType() {
		container.registerAs(new MyComplexClass("Dan"),MyTestInterface.class);
		Assert.assertTrue("Expecting true when matching entry has been registered as type", container.hasEntry(MyTestInterface.class));
	}
	
	@Test 
	public void testResolveAfterRegisterAsType() {
		container.registerAs(new MyComplexClass("Dan"),MyTestInterface.class);
		Assert.assertEquals("Expecting complex type to be resolved and usable", "My name is Dan", container.resolve(MyTestInterface.class).toString());
	}
	
	@Test
	public void testRemoveEntryByTypeAfterRegisterAs() {
		container.registerAs(new MyComplexClass("Dan"), MyTestInterface.class);
		container.remove(MyTestInterface.class);
		Assert.assertNull("Expecting resolve to return null after entry removed", container.resolve(MyComplexClass.class));
		Assert.assertNull("Expecting resolve to return null after entry removed", container.resolve(MyTestInterface.class));
	} 


}
