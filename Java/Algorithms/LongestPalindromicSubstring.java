class LongestPalindromicSubstring {
	static int low, high, max;

	static String findLongestPalindromicSubstring(String s) {
		low = high = max = 0;

		for (int i = 0; i < s.length(); i++) {
			extendPalindrome(s, i, i);
			extendPalindrome(s, i, i + 1);
		}

		return s.substring(low, high + 1);
	}

	static void extendPalindrome(String s, int left, int right) {
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		} 

		if(right - left - 1 > max) {
			max = right - left - 1;
			low = left + 1;
			high = right - 1;
		}
	}


	public static void main(String[] args) {
		String[] strs = new String[]{"racecar", "helloworld", "babd", "sitting_duck"};

		for (String s : strs) 
			System.out.print( "\nLowest palindric substring of " + s + " is " + findLongestPalindromicSubstring(s) );
	}
}