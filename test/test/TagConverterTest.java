package test;

import com.akjava.lib.common.tag.Tag;
import com.akjava.lib.common.tag.TagToStringConverter;

import junit.framework.TestCase;

public class TagConverterTest extends TestCase {

	public void testSimple1(){
		Tag tag=new Tag("hello");		
		doTest("<hello></hello>\n", tag);
	}
	public void testSimple2(){
		Tag tag=new Tag("hello");
		tag.setSingleTag(true);
		doTest("<hello/>\n",tag);
		
	}
	public void testSimple3(){
		Tag tag=new Tag("hello");
		tag.setText("my name");
		doTest("<hello>my name</hello>\n", tag);
	}
	public void testDepth2a(){
		Tag tag=new Tag("hello");
		tag.addChild(createTag("world","level1"));
		doTest("<hello>\n\t<world>level1</world>\n</hello>\n", tag);
	}
	public void testDepth2b(){
		Tag tag=new Tag("hello");
		tag.addChild(createTag("world","level1"));
		tag.addChild(createTag("world","level1b"));
		doTest("<hello>\n\t<world>level1</world>\n\t<world>level1b</world>\n</hello>\n", tag);
	}
	private Tag createTag(String tagName,String text){
		Tag tag=new Tag(tagName);
		tag.setText(text);
		return tag;
	}
	
	private void doTest(String collect,Tag tag){
		assertEquals(collect, TagToStringConverter.convert(tag));
	}
}
