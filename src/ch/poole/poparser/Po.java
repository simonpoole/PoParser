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
	
	Po(InputStream is) {
		PoParser pp = PoParser(is);
		m = pp.getMap();
	}
	
	String t(String id) {
		String r = m.get(id);
		return r != null ? r : id;
	}

}
