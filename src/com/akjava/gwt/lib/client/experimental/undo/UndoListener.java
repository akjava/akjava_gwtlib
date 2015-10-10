package com.akjava.gwt.lib.client.experimental.undo;

public interface UndoListener {
public void onExecute(SimpleUndoControler controler,Command command);
public void onUndo(SimpleUndoControler controler,Command command);
public void onRedo(SimpleUndoControler controler,Command command);
public void onUpdate(SimpleUndoControler controler,Command command);
}
