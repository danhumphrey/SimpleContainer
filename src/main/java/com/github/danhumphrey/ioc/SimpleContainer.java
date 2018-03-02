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
public class SimpleContainer implements ObjectContainer {

	private static Map<String, Object> entries;

	public SimpleContainer() {
		if (entries == null) {
			entries = new HashMap<String, Object>();
		}
	}

	/*
	 * @see com.github.danhumphrey.ioc.ObjectContainer#hasEntry(java.lang.reflect.Type)
	 */
	@Override
	public boolean hasEntry(Type type) {
		return entries.containsKey(type.getTypeName());
	}

	/*
	 * @see com.github.danhumphrey.ioc.ObjectContainer#hasNamedEntry(java.lang.String)
	 */
	@Override
	public boolean hasNamedEntry(String entryName) {
		return entries.containsKey(entryName);
	}
	
	/*
	 * @see com.github.danhumphrey.ioc.ObjectContainer#register(T)
	 */
	@Override
	public <T> void register(T entry) {
		entries.put(entry.getClass().getTypeName(), entry);
	}
	
	/*
	 * @see com.github.danhumphrey.ioc.ObjectContainer#register(java.lang.String, T)
	 */
	@Override
	public <T> void register(String entryName, T entry) {
		entries.put(entryName, entry);
	}
	
	/*
	 * @see com.github.danhumphrey.ioc.ObjectContainer#removeAll()
	 */
	@Override
	public void removeAll() {
		entries.clear();
	}
	
	/*
	 * @see com.github.danhumphrey.ioc.ObjectContainer#resolve(java.lang.reflect.Type)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T resolve(Type type) {
		return (T) entries.get(type.getTypeName());
	}
	
	/*
	 * @see com.github.danhumphrey.ioc.ObjectContainer#resolve(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T resolve(String entryName) {
		return (T) entries.get(entryName);
	}

	/* 
	 * @see com.github.danhumphrey.ioc.ObjectContainer#remove(java.lang.reflect.Type)
	 */
	@Override
	public <T> void remove(Type type) {
		entries.remove(type.getTypeName());
	}

	/* 
	 * @see com.github.danhumphrey.ioc.ObjectContainer#remove(java.lang.String)
	 */
	@Override
	public void remove(String entryName) {
		entries.remove(entryName);
	}

	/* 
	 * @see com.github.danhumphrey.ioc.ObjectContainer#registerAs(T, java.lang.Class)
	 */
	@Override
	public <T> void registerAs(T entry, Class<?> asType) {
		if (!asType.isInstance(entry)) {
			throw new IllegalArgumentException("The entry must extend or implement the asType");
		}
		entries.put(asType.getTypeName(), entry);
	}

}
