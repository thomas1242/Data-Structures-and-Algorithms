class LevenshteinDistance {

	static int getStringDifference(String s1, String s2) {
		if(s1.length() == 0) return s2.length();	// insert/remove all of S2's chars
		if(s2.length() == 0) return s1.length();	// insert/remove all of S1's chars	

		int removeFromS1 = getStringDifference( s1.substring(0, s1.length() - 1), s2.substring(0, s2.length()) ) + 1; // remove/edit last char of S1
		int removeFromS2 = getStringDifference( s1.substring(0, s1.length()), s2.substring(0, s2.length() - 1) ) + 1; // remove/edit last char of S2

		int diff = (s1.charAt(s1.length() - 1)) == (s2.charAt(s2.length() - 1)) ? 0 : 1;	// remove/edit last char either string, if necessary, to make last chars the same
		int removeFromBoth = getStringDifference( s1.substring(0, s1.length() - 1), s2.substring(0, s2.length() - 1) ) + diff;

		return Math.min(removeFromBoth, Math.min(removeFromS1, removeFromS2));
	}

	public static void main(String[] args) {
		String s1 = "kitten";
		String s2 = "sitting";
	
		System.out.println( getStringDifference(s1, s2) );
	}
}