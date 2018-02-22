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
	
	/**
	 * Resolves an entry from the container with the matching type. null will be
	 * returned if a matching entry cannot be found.
	 * 
	 * @param type the type of the entry you wish to resolve
	 * @return the matching entry or null if a matching entry could not be found
	 */
	@SuppressWarnings("unchecked")
	public <T> T resolve(Type type) {
		return (T) entries.get(type.getTypeName());
	}


	/**
	 * Resolves an entry from the container with the matching name. null will be
	 * returned if a matching entry cannot be found.
	 * 
	 * @param entryName the name of the entry to you wish to resolve
	 * @return the matching entry or null if a matching entry could not be found
	 */
	@SuppressWarnings("unchecked")
	public <T> T resolve(String entryName) {
		return (T) entries.get(entryName);
	}

	/**
	 * Removes an entry by type.
	 * 
	 * @param type the entry type to remove
	 */
	public <T> void remove(Type type) {
		entries.remove(type.getTypeName());
	}

	/**
	 * Removes an entry by name
	 * 
	 * @param entryName the name of the entry to remove
	 */
	public void remove(String entryName) {
		entries.remove(entryName);
	}


	/**
	 * Registers an entry as a particular superclass, interface or superinterface
	 * 
	 * @param entry the entry to store in the container
	 * @param asType the type to register the entry as
	 */
	public <T> void registerAs(T entry, Class<?> asType) {
		if (!asType.isInstance(entry)) {
			throw new IllegalArgumentException("The entry must extend or implement the asType");
		}
		entries.put(asType.getTypeName(), entry);
	}

}
