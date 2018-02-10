import java.util.List;
import java.util.LinkedList;

class LetterCombinationsOfPhoneNumber {

    // recursive solution
    static List<String> letterCombinations_1(String digits) {
        List<String> combinations = new LinkedList<>();

        recurse(digits, combinations, new StringBuilder());

        return combinations;
    }
    
    static void recurse(String digits,  List<String> letterCombinations, StringBuilder sb) {
        String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        for(char c : map[Character.getNumericValue(digits.charAt(0))].toCharArray())
            if(digits.length() == 1) 						// base case
                letterCombinations.add(sb.toString() + c); 
            else                     						// recursive step
                recurse(digits.substring(1),  letterCombinations, new StringBuilder(sb.toString() + c));        
    }

    // iterative solution 
	static List<String> letterCombinations_2(String digits) {
        String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        List<String> q = new LinkedList<>();        
        q.add("");
        
        for(int i = 0; i < digits.length(); i++) {
            while(q.get(0).length() < i + 1) {
                String curr = q.remove(0);
                for(int j = 0; j < map[ Character.getNumericValue(digits.charAt(i)) ].length(); j++) 
                    q.add( curr +  map[ Character.getNumericValue(digits.charAt(i)) ].charAt(j) );
            }
        }
        
        return q;
    }

	public static void main(String[] args) {
		List<String> combos = letterCombinations_1( "235" );

		for(String combo : combos) 
			System.out.print(combo + ", ");
		System.out.println("\n");

	}
}