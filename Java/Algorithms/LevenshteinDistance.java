class LevenshteinDistance {

	// The Levenshtein distance between two strings is the minimum number of single-character edits 
	// 		  (insertions, deletions or substitutions) required to change one string into the other.
	
	static int getStringDifference(String s1, String s2) {	   // O(3 ^ n)
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

	static int getStringDifference_dp(String s1, String s2) {	// O(n ^ 2)
		int len1 = s1.length(), len2 = s2.length();

		int[][] editDistance = new int[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) {
			for (int j = 0; j <= len2; j++) {
				if(i == 0)
					editDistance[i][j] = j;
				else if(j == 0)
					editDistance[i][j] = i;
				else if(s1.charAt(i - 1) == s2.charAt(j - 1))
					editDistance[i][j] = editDistance[i - 1][j - 1];
				else
					editDistance[i][j] = 1 + min(editDistance[i - 1][j], editDistance[i][j - 1], editDistance[i-1][j-1]);
			}
		}

		return editDistance[len1][len2];
	}

	static int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}

	public static void main(String[] args) {
		String s1 = "kitten";
		String s2 = "sitting";
		System.out.println( getStringDifference(s1, s2) );
		System.out.println( getStringDifference_dp(s1, s2) );

	}
}