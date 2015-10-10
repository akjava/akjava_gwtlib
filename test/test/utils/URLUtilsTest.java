package test.utils;



import junit.framework.TestCase;

import com.akjava.lib.common.utils.URLUtils;

public class URLUtilsTest extends TestCase{

	public void testSimple1(){
		String url="http://localhost/index.html";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
	
	public void testSimple2(){
		String url="https://localhost/index.html";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
	
	public void testSimple3(){
		String url="https://localhost:8888/index.html";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
	public void testSimple4(){
		String url="https://localhost:8888/test//index.html";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
	
	public void testSimple5(){
		String url="/test/index.html";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
	
	public void testSimple6(){
		String url="/test//index.html";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
	
	public void testSimple7(){
		String url="/";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
	public void testSimple8(){
		String url="../";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
	
	public void testSimple9(){
		String url="http://localhost";
		assertEquals(url,URLUtils.parseUrl(url).toString());
	}
}
