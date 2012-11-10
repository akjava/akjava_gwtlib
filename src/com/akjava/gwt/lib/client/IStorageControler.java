package com.akjava.gwt.lib.client;

public interface IStorageControler {

	public abstract boolean isAvailable();

	public abstract void setValue(String key, String value) throws StorageException;

	public abstract void setValue(String key, int value)  throws StorageException;

	public abstract void removeValue(String key)  throws StorageException;

	public abstract int getValue(String key, int defaultValue)  throws StorageException;

	public abstract String getValue(String key, String defaultValue)  throws StorageException;

}