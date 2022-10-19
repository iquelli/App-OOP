package prr.util;

import java.util.Comparator;

import java.io.Serial;
import java.io.Serializable;


public class KeyComparator implements Comparator<String>, Serializable {

    /** Serial number for serialization. */
    @Serial
	private static final long serialVersionUID = 202208091753L;

    @Override
    public int compare(String key1, String key2) {
        String text1 = extractString(key1);
        String text2 = extractString(key2);
        
        
        if((text1.isEmpty() && text2.isEmpty()) || text1.equals(text2)) {
        	if(text1.equals(key1) && text2.equals(key2))
        		return 0;
            return  extractInt(key1) - extractInt(key2);
        }
        
        return text1.compareTo(text2);
    }

    /**
     * Extracts numbers that were in a string and converts them to
     * integers.
     * 
     * @param s string with numbers
     * @return numbers in the form of int that were in the string
     */
    public int extractInt(String s) {
        String num = s.replaceAll("[A-Za-z]", "");
        return Integer.parseInt(num);
    }

    /**
     * Takes out numbers that are in string format.
     * 
     * @param s  string with numbers
     * @return string without numbers
     */
    public String extractString(String s) {
        String text = s.replaceAll("[0-9]", "");
        return text;
    }
}
