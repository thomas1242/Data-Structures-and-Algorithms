class LongestIncreasingSubsequence {

	static int longestIncreasingSubsequence(int[] arr) {

		int[] dp = new int[arr.length];
		for(int i = 0; i < dp.length; i++)
			dp[i] = 1;

		for (int i = 1; i < arr.length; i++) 
			for (int j = 0; j < i; j++) 
				if(arr[j] < arr[i] && (dp[j] + 1) > dp[i])
					dp[i] = dp[j] + 1;
			
		int max = 0;
		for (int i = 0; i < dp.length; i++) 
			max = Math.max(max, dp[i]);
		
		return max;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{1, 3, -2, 5, -10, 3, 4 -3, 7, -1};
		System.out.println( longestIncreasingSubsequence(arr) );

		arr = new int[]{ 10, 22, 9, 33, 21, 50, 41, 60 };
		System.out.println( longestIncreasingSubsequence(arr) );
	}
}