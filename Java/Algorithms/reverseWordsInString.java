class reverseWordsInString {

	static String reverseWords(String s) {
		char[] str = s.toCharArray();

		for(int i = 0; i < str.length; i++) {
		  
		    int j = i + 1;
		    while(j < str.length && str[j] != ' ')
		        j++;

		    int left = i;
		    int right = j - 1;
		    
		    while(left <= right) {
		        char temp = str[left];
		        str[left] = str[right];
		        str[right] = temp;
		        left++;
		        right--;
		    }
		        
		    i = j;
		}
		
       		return new String(str);
    	}

	public static void main(String[] args) {
		String s = "hello world one two three";
		System.out.println( reverseWords(s) );
	}
}
