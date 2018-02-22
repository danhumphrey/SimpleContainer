package com.github.danhumphrey.ioc;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * SimpleContainer is a simple Registry used to store and retrieve common
 * objects by type or identifier. Can be used with Dependency Injection for
 * sharing dependencies.
 * 
 * @author Dan Humphrey
 *
 */
public class SimpleContainer {

	private static Map<String, Object> entries;

	public SimpleContainer() {
		if (entries == null) {
			entries = new HashMap<String, Object>();
		}
	}

	/**
	 * Checks the entries for an entry with a matching type
	 * 
	 * @param type the type you want to check
	 * @return true if a matching entry was found and false otherwise
	 */
	public boolean hasEntry(Type type) {
		return entries.containsKey(type.getTypeName());
	}

	/**
	 * Checks the entries for an entry with a matching name
	 * 
	 * @param type the type you want to check
	 * @return true if a matching entry was found and false otherwise
	 */
	public boolean hasNamedEntry(String entryName) {
		return entries.containsKey(entryName);
	}
	
	/**
	 * Registers an entry in the container. If the container already has an entry of
	 * the same type it will be overwritten.
	 * 
	 * @param entry the entry to store in the container
	 */
	public <T> void register(T entry) {
		entries.put(entry.getClass().getTypeName(), entry);
	}
	
	/**
	 * Registers an entry in the container with an entry name which allows multiple
	 * entries of the same type to be registered.
	 * 
	 * @param entryName the unique name of the entry
	 * @param entry the entry to store in the container
	 */
	public <T> void register(String entryName, T entry) {
		entries.put(entryName, entry);
	}
	
	/**
	 * Removes all entries
	 */
	public void removeAll() {
		entries.clear();
	}

}
