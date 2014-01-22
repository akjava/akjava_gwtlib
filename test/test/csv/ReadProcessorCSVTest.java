package test.csv;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.csv.CSVProcessor;
import com.akjava.lib.common.csv.CSVReader;
import com.akjava.lib.common.csv.NewCSVReader;
import com.akjava.lib.common.utils.CSVUtils;
import com.google.common.io.CharSource;

public class ReadProcessorCSVTest extends TestCase{

	
	
	public void testLine(){
		String line="hello";
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0);
		
		String correct="row-size=1\n" +
				"column[0]-size=1\n" +
				"column[0][0]=hello";
		
		assertEquals(correct, result);
	}
	

	public void testLine2(){
		String line="hello,world";
		
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		result+="column[0][1]="+csvs.get(0).get(1)+"\n";
		
		String correct="row-size=1\n" +
				"column[0]-size=2\n" +
				"column[0][0]=hello\n" +
				"column[0][1]=world\n";
		
		assertEquals(correct, result);
	}
	
	//can contain single \"
	public void testLine4(){
		String line="h\"ello,world";
		
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		result+="column[0][1]="+csvs.get(0).get(1)+"\n";
		
		String correct="row-size=1\n" +
				"column[0]-size=2\n" +
				"column[0][0]=h\"ello\n" +
				"column[0][1]=world\n";
		
		assertEquals(correct, result);
	}
	
	//parse quote
	public void testLine5(){
		String line="\"hello\",\"world\"";
		
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		result+="column[0][1]="+csvs.get(0).get(1)+"\n";
		
		String correct="row-size=1\n" +
				"column[0]-size=2\n" +
				"column[0][0]=hello\n" +
				"column[0][1]=world\n";
		
		assertEquals(correct,result);
	}
	//quoted can contain separator
	public void testLine6(){
		String line="\"hello,\",\"wor,ld\"";
		
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		result+="column[0][1]="+csvs.get(0).get(1)+"\n";
		
		String correct="row-size=1\n" +
				"column[0]-size=2\n" +
				"column[0][0]=hello,\n" +
				"column[0][1]=wor,ld\n";
		
		assertEquals(correct,result);
	}
	
	//quoted can contain line-separator
	public void testLine7(){
		String line="\"hello\n\",\"wor\nld\"";
		
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		result+="column[0][1]="+csvs.get(0).get(1)+"\n";
		
		String correct="row-size=1\n" +
				"column[0]-size=2\n" +
				"column[0][0]=hello\n\n" +
				"column[0][1]=wor\nld\n";
		
		assertEquals(correct,result);
	}
	
	//this is invalid but read as 1 line
	public void testLine8(){
		String line="\"hello,world\n.";
		
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0);
		
		
		String correct="row-size=1\n" +
				"column[0]-size=1\n" +
				"column[0][0]=hello,world\n.\n";
		
		assertEquals(correct,result);
	}
	
	public void testLine9(){
		String line="\"" +
				"\"\"" +
				"\"";
		
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		
		
		String correct="row-size=1\n" +
				"column[0]-size=1\n" +
				"column[0][0]=\"\n";
		
		assertEquals(correct,result);
	}
	
	
	public void testLine3a(){
		String line="hello,world\n";
		int size=CSVUtils.splitLinesWithGuava(line).size();
		assertEquals(2, size);
	}
	/**
	 * first one is ignored but second is ignored by CharSource
	 */
	public void testLine3(){
		String line="hello,world\n";
		
		List<List<String>> csvs=null;
		CSVProcessor processor=new CSVProcessor(',');
		try {
			csvs=CharSource.wrap(line).readLines(processor);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		result+="column[0][1]="+csvs.get(0).get(1)+"\n";
		//result+="column[1][0]="+csvs.get(1).get(0)+"\n";
		
		String correct="row-size=1\n" +
				"column[0]-size=2\n" +
				"column[0][0]=hello\n" +
				"column[0][1]=world\n";
				//"column[1][0]=\n";
		
		assertEquals(correct, result);
	}
}
