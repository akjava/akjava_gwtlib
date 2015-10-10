package com.akjava.gwt.lib.client.experimental.undo;
public  interface Command{
	public void execute();
	public void undo();
	public void redo();
}