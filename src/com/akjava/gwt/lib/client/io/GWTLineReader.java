package com.akjava.gwt.lib.client.io;

import java.io.IOException;
import java.util.List;

import com.akjava.lib.common.utils.CSVUtils;
import com.google.common.io.LineProcessor;

public class GWTLineReader {
	private List<String> lines;
	private GWTLineReader(String text){
		lines=CSVUtils.splitLinesWithGuava(text);
		
	}
	
	public <T> T readLines(LineProcessor<T> processor) throws IOException {
		for(String line:lines){
			processor.processLine(line);
		}
		return processor.getResult();
	}
	
	public static GWTLineReader wrap(String text){
		return new GWTLineReader(text);
	}
}
