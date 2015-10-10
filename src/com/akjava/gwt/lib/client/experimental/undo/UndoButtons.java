package com.akjava.gwt.lib.client.experimental.undo;

import com.akjava.gwt.lib.client.experimental.undo.SimpleUndoControler.UndoStateListener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class UndoButtons extends HorizontalPanel implements UndoStateListener{
private SimpleUndoControler controler;
public SimpleUndoControler getControler() {
	return controler;
}

private Button undoButton;
private Button redoButton;

public UndoButtons(final SimpleUndoControler controler) {
	super();
	this.controler = controler;
	controler.setListener(this);
	
	undoButton = new Button("Undo");
	undoButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			controler.undo();
		}
	});
	add(undoButton);
	
	redoButton = new Button("Redo");
	redoButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			controler.redo();
		}
	});
	add(redoButton);
	
	updateState(false,false);
}

@Override
public void updateState(boolean undoable, boolean redoable) {
	undoButton.setEnabled(undoable);
	redoButton.setEnabled(redoable);
}

}
