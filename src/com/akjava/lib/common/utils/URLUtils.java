package com.akjava.lib.common.utils;

import com.google.common.base.Predicate;

public class URLUtils {
	private URLUtils(){}
	
	public static URLInfo parseUrl(String url){
		int pend=url.indexOf("://");
		String protocol=null;
		if(pend!=-1){
			protocol=url.substring(0,pend);
		}
		if(protocol!=null){
			url=url.substring(pend+3);
			}
		int pageStart=url.indexOf("/");
		String page=null;
		String domainAndPort=null;
		if(pageStart!=-1){
			page=url.substring(pageStart);
			//TODO support query&hash
			domainAndPort=url.substring(0,pageStart);
		}else{
			domainAndPort=url;
		}
		String domain=null;
		String port=null;
		int portStart=domainAndPort.indexOf(":");
		if(portStart!=-1){
			port=domainAndPort.substring(portStart+1);
			domain=domainAndPort.substring(0,portStart);
		}else{
			domain=domainAndPort;
		}
		
		return new URLInfo(protocol,domain,port,page);
	}
	
	public static class SamePagePredicaate implements Predicate<String>{
		private String targetPath;

		public SamePagePredicaate(String targetPath) {
			super();
			this.targetPath = targetPath;
		}

		@Override
		public boolean apply(String url) {
			return targetPath.equals(parseUrl(url).getPage());
		}
		
	}
	
	public static class URLInfo{
		private String port;
		private String protocol;
		public URLInfo(String protocol, String host, String port, String page) {
			super();
			this.port = port;
			this.protocol = protocol;
			this.host = host;
			this.page = page;
		}
		private String host;
		private String page;
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		public String getProtocol() {
			return protocol;
		}
		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getPage() {
			return page;
		}
		public void setPage(String page) {
			this.page = page;
		}
		
		@Override
		public String toString(){
			String result="";
			if(protocol!=null){
				result+=protocol+"://";
			}
			if(host!=null){
				result+=host;
			}
			if(port!=null){
				result+=":"+port;
			}
			if(page!=null){
				result+=page;
			}
			return result;
		}
		
	}
}
