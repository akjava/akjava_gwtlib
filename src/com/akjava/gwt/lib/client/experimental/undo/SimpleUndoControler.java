package com.akjava.gwt.lib.client.experimental.undo;

import java.util.Stack;

import com.google.common.base.Optional;

//TODO create interface?
public class SimpleUndoControler {

	private Stack<Command> undoHistory;
    private Stack<Command> redoHistory;

    private UndoStateListener listener;
    private UndoListener undoListener;
    
    
    public UndoListener getUndoListener() {
		return undoListener;
	}

	public void setUndoListener(UndoListener undoListener) {
		this.undoListener = undoListener;
	}

	public UndoStateListener getListener() {
		return listener;
	}

	public void setListener(UndoStateListener listener) {
		this.listener = listener;
	}

	public SimpleUndoControler() {
         undoHistory = new Stack<Command>();
         redoHistory = new Stack<Command>();
    }

	public void update(Command command){
		fireUpdate(command);
	}
	
    public void execute(Command command) {
        command.execute();
        redoHistory.clear();
        undoHistory.push(command);
        
        fireUndoState(true,false);
        fireExecute(command);
    }
    
    private void fireUpdate(Command command){
    	if(undoListener==null){
    		return;
    	}
    	undoListener.onExecute(this, command);
    }
    
    private void fireExecute(Command command){
    	if(undoListener==null){
    		return;
    	}
    	undoListener.onExecute(this, command);
    }
    private void fireUndo(Command command){
    	if(undoListener==null){
    		return;
    	}
    	undoListener.onUndo(this, command);
    }
    private void fireRedo(Command command){
    	if(undoListener==null){
    		return;
    	}
    	undoListener.onRedo(this, command);
    }
    
    private void fireUndoState(boolean undoable,boolean redoable){
    	if(listener==null){
    		return;
    	}
    	listener.updateState(undoable, redoable);
    }
    
    public void undo() {
        if (undoHistory.isEmpty()) {
             return;
        }
        Command command = undoHistory.pop();
	command.undo();
	redoHistory.push(command);
	
	fireUndoState(undoHistory.isEmpty()?false:true,true);
	fireUndo(command);
    }

    public void redo() {
        if (redoHistory.isEmpty()) {
	    return;
	}
        Command command = redoHistory.pop();
	command.redo();
	undoHistory.push(command);
	
	 fireUndoState(true,redoHistory.isEmpty()?false:true);
	 fireRedo(command);
    }
    
    public Optional<Command> getLastUndoCommand(){
    	if(undoHistory.isEmpty()){
    		return Optional.absent();
    	}else{
    		return Optional.of(undoHistory.get(undoHistory.size()-1));
    	}
    }
    
    public void clear(){
    	undoHistory.clear();
    	redoHistory.clear();
    	
    	 fireUndoState(false,false);
    }
}
