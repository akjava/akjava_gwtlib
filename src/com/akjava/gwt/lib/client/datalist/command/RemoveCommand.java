package com.akjava.gwt.lib.client.datalist.command;

import com.akjava.gwt.lib.client.datalist.ItemIOControler;
import com.akjava.gwt.lib.client.datalist.SimpleTextData;

public class RemoveCommand implements DataListCommand{

	private ItemIOControler controler;
	private SimpleTextData data;
	@Override
	public void execute() {
		//skip
	}

	@Override
	public void undo() {
		controler.execUpdate(data,false);	
	}

	public RemoveCommand(ItemIOControler controler, SimpleTextData data) {
		super();
		this.controler = controler;
		this.data = data;
	}

	@Override
	public void redo() {
		controler.execDelete(data.getId(),false);
	}

}
