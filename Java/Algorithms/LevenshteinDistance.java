class LevenshteinDistance {

	// The Levenshtein distance between two strings is the minimum number of single-character edits 
	// 		  (insertions, deletions or substitutions) required to change one string into the other.
	
	static int getStringDifference(String s1, String s2) {
		int len1 = s1.length(), len2 = s2.length();

		if(len1 == 0) return len2;	
		if(len2 == 0) return len1;		

		if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1))
			return getStringDifference( s1.substring(0, len1 - 1), s2.substring(0, len2 - 1) );

		int editS1 = getStringDifference( s1.substring(0, len1 - 1), s2.substring(0, len2) ); 
		int editS2 = getStringDifference( s1.substring(0, len1), s2.substring(0, len2 - 1) ); 
		int editBoth = getStringDifference( s1.substring(0, len1 - 1), s2.substring(0, len2 - 1) );

		return 1 + Math.min(editBoth, Math.min(editS1, editS2));
	}

	public static void main(String[] args) {
		String s1 = "kitten";
		String s2 = "sitting";
		System.out.println( getStringDifference(s1, s2) );
	}
}