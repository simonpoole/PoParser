package ch.poole.poparser;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Couple of tests for PoParser
 * @author Simon Poole
 *
 */
public class PoParserTest {

	@Test
  	public void regressionTest() 
  	{
		String poFile = "testinput/preset_tr.po";
		String resultsFile = poFile + "-results";
		BufferedWriter bwResults = null;
		BufferedReader brResults = null;
		String r = null;
		String expected = null;
		try
    	{ 
    		Po po = new Po(new FileInputStream(poFile));
    		
			try {
				brResults = new BufferedReader(new InputStreamReader(new FileInputStream(resultsFile), "UTF8"));
			} catch (FileNotFoundException fnfex) {
				System.out.println("File not found " + fnfex.toString()); // don't fail
			} catch (UnsupportedEncodingException ueex) {
				fail(ueex.toString());
			}
			try {
				bwResults = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultsFile +"-temp"), "UTF8"));
			} catch (UnsupportedEncodingException ueex) {
				fail(ueex.toString());
			}

      		try
      		{
       			Map<String,HashMap<String,String>> m = po.getMap();
       			for (String k:m.keySet()) {
       				for (String s:m.get(k).keySet()) {
       					r = po.t(s);
       					bwResults.write(encodeNewlines(r)+"\n");
       					if (brResults != null) {
       						expected = decodeNewlines(brResults.readLine());
       						assertEquals(r,expected);
       					}
       					r = po.t("test",s);
       					bwResults.write(encodeNewlines(r)+"\n");
       					if (brResults != null) {
       						expected = decodeNewlines(brResults.readLine());
       						assertEquals(r,expected);
       					}
       				}
       			}
      		}
      		catch (Exception e)
      		{
      			fail("Caught an exception " + e.getMessage());
      		}
      		catch (Error e)
      		{
      		    System.out.println("error " + e.getMessage());
      			fail("Caught an error " + e.getMessage());
      		} 	
    	} catch (FileNotFoundException fnfex)
    	{
      		fail("File not found " + fnfex.toString());
    	} catch (ParseException pex) {
    		fail(pex.toString());
			pex.printStackTrace();
		} catch (AssertionError ae) {
			System.out.println("Assertion failed for (expected) >" + expected);
			System.out.println("vs                        (got) >" + r);
			throw ae;
		} finally {
			try {
				if (bwResults != null) {
					bwResults.close();
				}
				if (brResults != null) {
					brResults.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
  	}

	private String encodeNewlines(String r) {
		return r.replace((CharSequence)"\n", (CharSequence)"\\n"); 
	}
	
	private String decodeNewlines(String r) {
		return r.replace((CharSequence)"\\n", "\n"); 
	}

}
