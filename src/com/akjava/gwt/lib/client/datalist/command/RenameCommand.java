package com.akjava.gwt.lib.client.datalist.command;

import com.akjava.gwt.lib.client.datalist.ItemIOControler;
import com.akjava.gwt.lib.client.experimental.undo.SimpleUndoControler.Command;

public class RenameCommand implements Command{
	private ItemIOControler controler;
	private String oldName;
	private String newName;
	@Override
	public void execute() {
		//skip
	}

	@Override
	public void undo() {
		controler.execRename(oldName,false);	
	}

	public RenameCommand(ItemIOControler controler, String oldName,String newName) {
		super();
		this.controler = controler;
		this.oldName=oldName;
		this.newName=newName;
	}

	@Override
	public void redo() {
		controler.execRename(newName, false);
	}
}
