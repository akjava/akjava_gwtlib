package test.functions;

import junit.framework.TestCase;

import com.akjava.lib.common.functions.ColorFunctions;
import com.akjava.lib.common.graphics.RGBA;
import com.akjava.lib.common.utils.ColorUtils;

public class ColorFunctionsTest extends TestCase{

	public void testHex1(){
		String text="#fff";
		String correct="rgba(255,255,255,1.0)";
		RGBA rgba=ColorFunctions.getStringToRGBAFunction().apply(text);
		
		assertEquals(correct, rgba.toString());
	}
	
	public void testHex2(){
		String text="#ff0000";
		String correct="rgba(255,0,0,1.0)";
		RGBA rgba=ColorFunctions.getStringToRGBAFunction().apply(text);
		
		assertEquals(correct, rgba.toString());
	}
	//invalid case,need 3 or 6 value
	public void testHex3(){
		String text="#ff";
		
		RGBA rgba=ColorFunctions.getStringToRGBAFunction().apply(text);
		
		assertTrue(rgba==null);
	}
	
	//invalid case,must start with #
		public void testHex4(){
			String text="ffffff";
			
			RGBA rgba=ColorFunctions.getStringToRGBAFunction().apply(text);
			
			assertTrue(rgba==null);
		}
		
		public void testRGB1(){
			String text="rgb(255,0,0)";
			String correct="rgba(255,0,0,1.0)";
			RGBA rgba=ColorFunctions.getStringToRGBAFunction().apply(text);
			assertEquals(correct, rgba.toString());
		}
		
		public void testRGBA1(){
			String text="rgba(255,0,0,0.5)";
			String correct="rgba(255,0,0,0.5)";
			RGBA rgba=ColorFunctions.getStringToRGBAFunction().apply(text);
			assertEquals(correct, rgba.toString());
		}
		
		public void testEquals(){
			String test1="rgb(255,0,0)";
			String test2="#ff0000";
			
			//assertEquals(ColorFunctions.getStringToRGBAFunction().apply(test1),ColorFunctions.getStringToRGBAFunction().apply(test2));
			
			boolean eq=ColorUtils.getColorEquivalance().equivalent(test1, test2);
			
			//boolean eq=Equivalence.equals().onResultOf(ColorFunctions.getStringToRGBAFunction()).equivalent(test1, test2);
			assertTrue(eq);
		}
		
}
