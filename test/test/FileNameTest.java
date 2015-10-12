package test;

import junit.framework.TestCase;

import com.akjava.lib.common.utils.FileNames;


public class FileNameTest extends TestCase{

	public void testExtension1(){
		String base="test.jpg";
		String collect="jpg";
		assertEquals(collect, FileNames.getExtension(base));
	}
	public void testExtension2(){
		String base="test";
		String collect="";
		assertEquals(collect, FileNames.getExtension(base));
	}
	
	public void testRemovedExtension1(){
		String base="test.jpg";
		String collect="test";
		assertEquals(collect, FileNames.getRemovedExtensionName(base));
	}
	public void testRemovedExtension2(){
		String base="test";
		String collect="test";
		assertEquals(collect, FileNames.getRemovedExtensionName(base));
	}
	
	public void testChangedExtension1(){
		String base="test.jpg";
		String collect="test.gif";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getChangedExtensionName(base,"gif"));
	}
	public void testChangedExtension2(){
		String base="test";
		String collect="test";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getChangedExtensionName(base,"gif"));
	}
	
	public void testIndexPath1(){
		String base="/base/test/";
		String collect="/base/test/index.html";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getIndexedPath(base,"index.html"));
	}
	
	/**
	 * no need index
	 */
	public void testIndexPath2(){
		String base="/base/test/page2.html";
		String collect="/base/test/page2.html";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getIndexedPath(base,"index.html"));
	}
	
	public void testIndexPath3(){
		String base="/base/test";
		String collect="/base/test/index.html";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getIndexedPath(base,"index.html"));
	}
	
	public void testDirectory1(){
		String base="/base/test/";
		String collect="/base/test/";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getDirectoryPath(base,true));
	}
	

	public void testDirectory2(){
		String base="/base/test";
		String collect="/base/test/";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getDirectoryPath(base,true));
	}
	

	public void testDirectory3(){
		String base="/base/test/hello.html";
		String collect="/base/test/";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getDirectoryPath(base,true));
	}
	public void testDirectory4(){
		String base="/base/testhello.html";
		String collect="/base/";
		assertEquals(collect, FileNames.as(FileNames.SLASH).getDirectoryPath(base,true));
	}
	
	public void testDownloadName1(){
		String test="hello";
		String correct="hello";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
	public void testDownloadName2(){
		String test="hello()";
		String correct="hello";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
	public void testDownloadName3(){
		String test="hello(1)";
		String correct="hello";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
	public void testDownloadName4(){
		String test="hello (1)";
		String correct="hello";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
	public void testDownloadName5(){
		String test="hello (10)";
		String correct="hello";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
	public void testDownloadName6(){
		String test="hello (10";
		String correct="hello (10";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
	public void testDownloadName7(){
		String test="hello 10)";
		String correct="hello 10)";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
	public void testDownloadName8(){
		String test="hello (-10)";
		String correct="hello (-10)";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
	public void testDownloadName9(){
		String test="hello2";
		String correct="hello2";
		assertEquals(correct, FileNames.getRemovedLastDownloadNumbers(test));
	}
}
