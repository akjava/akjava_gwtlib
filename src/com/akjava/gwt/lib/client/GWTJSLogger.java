package com.akjava.gwt.lib.client;

import com.akjava.lib.common.utils.log.GWTLogger;

public class GWTJSLogger implements GWTLogger{

	@Override
	public void log(String text) {
		LogUtils.log(text);
	}

}
