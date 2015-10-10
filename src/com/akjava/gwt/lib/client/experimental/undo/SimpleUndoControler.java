package com.akjava.gwt.lib.client.experimental.undo;

import java.util.Stack;

import com.google.common.base.Optional;

//TODO create interface?
public class SimpleUndoControler {

	public static interface UndoStateListener{
		public void updateState(boolean undoable,boolean redoable);
	}
	
	public static interface Command{
		public void execute();
		public void undo();
		public void redo();
	}
	
	private Stack<Command> undoHistory;
    private Stack<Command> redoHistory;

    private UndoStateListener listener;
    
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

    public void execute(Command command) {
        command.execute();
        redoHistory.clear();
        undoHistory.push(command);
        
        fireUndoState(true,false);
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
    }

    public void redo() {
        if (redoHistory.isEmpty()) {
	    return;
	}
        Command command = redoHistory.pop();
	command.redo();
	undoHistory.push(command);
	
	 fireUndoState(true,redoHistory.isEmpty()?false:true);
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
