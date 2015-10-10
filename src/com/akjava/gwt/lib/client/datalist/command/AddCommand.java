package com.akjava.gwt.lib.client.datalist.command;

import com.akjava.gwt.lib.client.datalist.ItemIOControler;
import com.akjava.gwt.lib.client.datalist.SimpleTextData;

public class AddCommand implements DataListCommand{

	private ItemIOControler controler;
	private SimpleTextData data;
	public AddCommand(ItemIOControler controler, SimpleTextData data) {
		super();
		this.controler = controler;
		this.data = data;
	}

	@Override
	public void execute() {
		//skip
	}

	@Override
	public void undo() {
		controler.execDelete(data.getId(),false);
	}

	@Override
	public void redo() {
		controler.execUpdate(data,false);
	}

}
