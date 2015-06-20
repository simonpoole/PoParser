package ch.poole.poparser;

import java.io.InputStream;
import java.util.Map;

/**
 * Tiny wrapper around the parser
 * @author Simon Poole
 *
 */
public class Po {
	
	Map<String, String> m = null;
	
	/**
	 * Parse the input stream in to a map
	 * @param is
	 * @throws ParseException
	 */
	Po(InputStream is) throws ParseException {
		PoParser pp = new PoParser(is);
		m = pp.getMap();
	}
	
	/**
	 * Return the translation of a string or the string itself
	 * if no translation exists.
	 * @param id
	 * @return
	 */
	String t(String id) {
		String r = m.get(id);
		return r != null ? r : id;
	}

	/**
	 * Return the full map 
	 * @return
	 */
	public Map<String, String> getMap() {
		return m;
	}
}
