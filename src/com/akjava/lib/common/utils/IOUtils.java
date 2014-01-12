package com.akjava.lib.common.utils;

import java.io.IOException;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class IOUtils {
	private IOUtils(){}
	public static String readResourceAsUTF8Text(String path){
		try {
			URL url = Resources.getResource(path);
			String text = Resources.toString(url, Charsets.UTF_8);
			return text;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
