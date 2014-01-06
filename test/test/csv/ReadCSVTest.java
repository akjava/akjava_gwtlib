package test.csv;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.csv.CSVParser;
import com.akjava.lib.common.csv.CSVReader;

public class ReadCSVTest extends TestCase{

	public void testLine1(){
		String line="hello";
		CSVReader reader=new CSVReader(line);
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("hello",lines.get(0)[0]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	public void testLine2(){
		String line="\"hello\"";
		CSVReader reader=new CSVReader(line);
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("hello",lines.get(0)[0]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	public void testLine3(){
		String line="hello\n";
		CSVReader reader=new CSVReader(line);
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("hello",lines.get(0)[0]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	public void testLine4(){
		String line="hello\tworld\n";
		CSVReader reader=new CSVReader(line,'\t');
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("hello",lines.get(0)[0]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	public void testLine5(){
		String line="hello\tworld\n";
		CSVReader reader=new CSVReader(line,'\t');
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("world",lines.get(0)[1]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	public void testLine6(){
		String line="hello\t\"world\"\n";
		CSVReader reader=new CSVReader(line,'\t');
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("world",lines.get(0)[1]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	
	public void testLineContainLine(){
		String line="hello\t\"world\nworld\"\n";
		CSVReader reader=new CSVReader(line,'\t');
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("world\nworld",lines.get(0)[1]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	//this is little bit strange behavior?
	public void testLineContainQuote1(){
		String line="hello\"\"\t\"world\nworld\"\n";
		CSVReader reader=new CSVReader(line,'\t');
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("hello\"",lines.get(0)[0]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	
	/*
	 * sadly not work fine
	public void testLineContainQuote3(){
		String line="hello\"";
		CSVReader reader=new CSVReader(line,'\t','"',true);
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("hello\"",lines.get(0)[0]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	*/

	
	public void testLineContainQuote2(){
		String line="\"hello\"\"\"";
		CSVReader reader=new CSVReader(line,'\t');
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("hello\"",lines.get(0)[0]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
	
	public void testLineContainQuote3(){
		String line="\"hello\t\"\tabc";
		CSVReader reader=new CSVReader(line,'\t');
		try {
			List<String[]> lines=reader.readAll();
			assertEquals("hello\t",lines.get(0)[0]);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail();
	}
}
