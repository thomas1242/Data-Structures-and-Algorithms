import java.util.List;
import java.util.LinkedList;

class LetterCombinationsOfPhoneNumber {

	static List<String> letterCombinations(String digits) {
        String[] dict = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        List<String> q = new LinkedList<>();        
        q.add("");
        
        for(int i = 0; i < digits.length(); i++) {
            while(q.get(0).length() < i + 1) {
                String curr = q.remove(0);
                for(int j = 0; j < dict[ Character.getNumericValue(digits.charAt(i)) ].length(); j++) 
                    q.add( curr +  dict[ Character.getNumericValue(digits.charAt(i)) ].charAt(j) );
            }
        }
        
        return q;
    }

	public static void main(String[] args) {
		String input = "23";
		List<String> combos = letterCombinations( input );

		for(String combo : combos) 
			System.out.print(combo + ", ");
		System.out.println("\n");

		input = "235";
		combos = letterCombinations( input );

		for(String combo : combos) 
			System.out.print(combo + ", ");
	}
}