package ch.poole.poparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Couple of tests for PoParser
 * @author Simon Poole
 *
 */
public class Test {

	
  	public static void main(String args []) 
  	{
  	  	try
    	{ 
    		Po po = new Po(new FileInputStream("testinput/preset_de.po"));

      		try
      		{
       			Map<String,String> m = po.getMap();
       			for (String k:m.keySet()) {
					System.out.println(k + " " + m.get(k));
       			}
       			System.out.println("===========================================================");
       			String[] tests = {"Motorway","doesn't exist"};
       			for (String s:tests) {
       				String r = po.t(s);
       				System.out.println("Translating " + s +  " to " + r);
       		    }
      		}
      		catch (Exception e)
      		{
        		System.out.println("Caught an exception");
        		System.out.println(e.getMessage());
       	 	
      		}
      		catch (Error e)
      		{
        		System.out.println("Caught an exception");
        		System.out.println(e.getMessage());
        	
      		} 	
    	} catch (FileNotFoundException fnfex)
    	{
      		System.out.println("File not found " + fnfex.toString());
    	} catch (ParseException pex) {
    		System.out.println("Parser exception " + pex.toString());
			pex.printStackTrace();
		}
  	}

}
