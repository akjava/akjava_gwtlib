package com.akjava.gwt.lib.client;

public interface IStorageControler {

	public abstract boolean isAvailable();

	public abstract void setValue(String key, String value);

	public abstract void setValue(String key, int value);

	public abstract void removeValue(String key);

	public abstract int getValue(String key, int defaultValue);

	public abstract String getValue(String key, String defaultValue);

}