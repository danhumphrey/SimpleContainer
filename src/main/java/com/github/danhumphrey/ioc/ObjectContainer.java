package com.github.danhumphrey.ioc;

import java.lang.reflect.Type;

public interface ObjectContainer {

	/**
	 * Checks the entries for an entry with a matching type
	 * 
	 * @param type the type you want to check
	 * @return true if a matching entry was found and false otherwise
	 */
	boolean hasEntry(Type type);

	/**
	 * Checks the entries for an entry with a matching name
	 * 
	 * @param type the type you want to check
	 * @return true if a matching entry was found and false otherwise
	 */
	boolean hasNamedEntry(String entryName);

	/**
	 * Registers an entry in the container. If the container already has an entry of
	 * the same type it will be overwritten.
	 * 
	 * @param entry the entry to store in the container
	 */
	<T> void register(T entry);

	/**
	 * Registers an entry in the container with an entry name which allows multiple
	 * entries of the same type to be registered.
	 * 
	 * @param entryName the unique name of the entry
	 * @param entry the entry to store in the container
	 */
	<T> void register(String entryName, T entry);

	/**
	 * Removes all entries
	 */
	void removeAll();

	/**
	 * Resolves an entry from the container with the matching type. null will be
	 * returned if a matching entry cannot be found.
	 * 
	 * @param type the type of the entry you wish to resolve
	 * @return the matching entry or null if a matching entry could not be found
	 */
	<T> T resolve(Type type);

	/**
	 * Resolves an entry from the container with the matching name. null will be
	 * returned if a matching entry cannot be found.
	 * 
	 * @param entryName the name of the entry to you wish to resolve
	 * @return the matching entry or null if a matching entry could not be found
	 */
	<T> T resolve(String entryName);

	/**
	 * Removes an entry by type.
	 * 
	 * @param type the entry type to remove
	 */
	<T> void remove(Type type);

	/**
	 * Removes an entry by name
	 * 
	 * @param entryName the name of the entry to remove
	 */
	void remove(String entryName);

	/**
	 * Registers an entry as a particular superclass, interface or superinterface
	 * 
	 * @param entry the entry to store in the container
	 * @param asType the type to register the entry as
	 */
	<T> void registerAs(T entry, Class<?> asType);

}