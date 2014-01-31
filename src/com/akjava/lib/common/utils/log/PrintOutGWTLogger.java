package com.akjava.lib.common.utils.log;

import java.io.PrintStream;

public class PrintOutGWTLogger implements GWTLogger{
private PrintStream printStream;
	public PrintOutGWTLogger(PrintStream printStream) {
	super();
	this.printStream = printStream;
}
	@Override
	public void log(String text) {
		printStream.println(text);	
	}

}
