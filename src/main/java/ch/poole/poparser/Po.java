package ch.poole.poparser;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

/**
 * Tiny wrapper around the parser
 * @author Simon Poole
 *
 */
public class Po implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,HashMap<String,String>> m = null;
	
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
	 * Add strings where missing from a further file,
	 * can be called multiple times.
	 * @param is
	 * @throws ParseException
	 */
	public void addFallback(InputStream is) throws ParseException {
		PoParser pp = new PoParser(is);
		Map<String,HashMap<String,String>> fallback = pp.getMap();
		for (Entry<String,HashMap<String,String>> entry:m.entrySet()) { // loop over contexts
		    String ctxt = entry.getKey();
			Map<String,String> translations = entry.getValue();
			Map<String,String> fallbackTranslations = fallback.get(ctxt);
			if (fallbackTranslations == null) { // skip
				continue;
			}
			for (String untranslated:new TreeSet<String>(translations.keySet())) { // shallow copy needed
				if (translations.get(untranslated)==null) {
					String fallbackTranslation = fallbackTranslations.get(untranslated);
					if (fallbackTranslation!=null) {
						translations.put(untranslated,fallbackTranslation);
					}
				}
			}
		}
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
	 * Return the translation of a string or the string itself
	 * if no translation exists, considering context.
	 * @param id
	 * @return
	 */
	public String t(String ctxt, String id) {
		Map<String, String> mctxt = m.get(ctxt);
		if (mctxt != null) {
			String r = mctxt.get(id);
			return r != null ? r : id;
		} 
		return id;
	}

	
	/**
	 * Return the full map 
	 * @return a map of maps (one per context) of the source and translations strings
	 */
	public Map<String,HashMap<String,String>> getMap() {
		return m;
	}
}
