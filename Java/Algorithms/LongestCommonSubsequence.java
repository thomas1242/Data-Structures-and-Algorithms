class LongestCommonSubsequence {

	static int longestCommonSubsequence(String s1, String s2) {	// O(3 ^ n)
		int len1 = s1.length(), len2 = s2.length();

		if(len1 == 0 || len2 == 0) 
			return 0;

		if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1))
			return 1 + longestCommonSubsequence(s1.substring(0, len1 - 1), s2.substring(0, len2 - 1));
		else
			return Math.max( longestCommonSubsequence(s1.substring(0, len1 - 1), s2), longestCommonSubsequence(s1, s2.substring(0, len2 - 1)) );
	}

	static int longestCommonSubsequence_dp(String s1, String s2) { // O(n ^ 2)
		int len1 = s1.length(), len2 = s2.length();

		if(len1 == 0 || len2 == 0) 
			return 0;

		int[][] lcs = new int[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) {
		 	for (int j = 0; j <= len2; j++) {
		 		if(i == 0 || j == 0)
		 			lcs[i][j] = 0;
		 		else if (s1.charAt(i - 1) == s2.charAt(j - 1))
		 			lcs[i][j] = 1 + lcs[i - 1][j - 1];
		 		else
		 			lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
		 	}
		}

		return lcs[len1][len2];
	} 

	public static void main(String[] args) {
		String s1 = "ABCDGH";
		String s2 = "AEDFHR";

		System.out.println(    longestCommonSubsequence(s1, s2) );
		System.out.println( longestCommonSubsequence_dp(s1, s2) );
	}
}