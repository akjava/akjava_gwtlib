package com.akjava.gwt.lib.client.experimental.undo;

public interface UndoStateListener{
	public void updateState(boolean undoable,boolean redoable);
}