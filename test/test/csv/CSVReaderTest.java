package test.csv;

import java.util.List;

import junit.framework.TestCase;

import com.akjava.lib.common.csv.NewCSVReader;

public class CSVReaderTest extends TestCase{

	public void testLine(){
		String line="hello";
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
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
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
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
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
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
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
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
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
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
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
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
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		
		
		String correct="row-size=1\n" +
				"column[0]-size=1\n" +
				"column[0][0]=hello,world\n.\n";
		
		assertEquals(correct,result);
	}
	
	public void testLine9(){
		String line="\"" +
				"\"\"" +
				"\"";
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		
		
		String correct="row-size=1\n" +
				"column[0]-size=1\n" +
				"column[0][0]=\"\n";
		
		assertEquals(correct,result);
	}
	
	/**
	 * first one is ignored but second is recognized
	 */
	public void testLine3(){
		String line="hello,world\n";
		
		NewCSVReader reader=new  NewCSVReader(line,',');
		List<List<String>> csvs=reader.readAllAsList();
		
		String result="row-size="+csvs.size()+"\n";
		result+="column[0]-size="+csvs.get(0).size()+"\n";
		result+="column[0][0]="+csvs.get(0).get(0)+"\n";
		result+="column[0][1]="+csvs.get(0).get(1)+"\n";
		result+="column[1][0]="+csvs.get(1).get(0)+"\n";
		
		String correct="row-size=2\n" +
				"column[0]-size=2\n" +
				"column[0][0]=hello\n" +
				"column[0][1]=world\n"+
				"column[1][0]=\n";
		
		assertEquals(correct, result);
	}
	
}
