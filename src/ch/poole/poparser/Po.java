package ch.poole.poparser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Tiny wrapper around the parser
 * @author Simon Poole
 *
 */
public class Po {
	
	Map<String,HashMap<String,String>> m = null;
	
	/**
	 * Parse the input stream in to a map
	 * @param is
	 * @throws ParseException
	 */
	public Po(InputStream is) throws ParseException {
		PoParser pp = new PoParser(is);
		m = pp.getMap();
	}
	
	/**
	 * Return the translation of a string or the string itself
	 * if no translation exists.
	 * @param id
	 * @return
	 */
	public String t(String id) {
		String r = m.get(null).get(id);
		return r != null ? r : id;
	}

	/**
	 * Return the translation of a string or the string itself considering context
	 * if no translation exists.
	 * @param id
	 * @return
	 */
	public String t(String ctxt, String id) {
		Map<String, String> mctxt = m.get(ctxt);
		if (mctxt != null) {
			String r = mctxt.get(id);
			return r != null ? r : id;
		} else {
			mctxt = m.get(null);
			if (mctxt != null) {
				String r = mctxt.get(id);
				return r != null ? r : id;
			} 
		}
		return id;
	}

	
	/**
	 * Return the full map 
	 * @return
	 */
	public Map<String,HashMap<String,String>> getMap() {
		return m;
	}
}
