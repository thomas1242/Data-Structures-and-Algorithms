import java.util.LinkedList;
import java.util.Queue;

class LetterCombinationsOfPhoneNumber {

	static String[] getLetterCombinations(String input) {

		String[] map = new String[]{"", "ABC", "DEF", "GHI", "JKL", "MNO", "PQR"};

		Queue<String> q = new LinkedList<>();
		q.add("");

		for(int i = 0; i < input.length(); i++) {
			while(q.peek().length() < i + 1) {

				String part = q.remove();
				for(int j = 0; j < map[ Character.getNumericValue( input.charAt(i) ) ].length(); j++) 
					q.add( (part + map[ Character.getNumericValue( input.charAt(i) ) ].charAt(j) ) );

			}	
		}
			
		String[] res = new String[ q.size() ];
		int n = 0;
		while( !q.isEmpty() ) 
			res[n++] = q.remove();
	
		return res;		
	}

	public static void main(String[] args) {

		String input = "23";
		String[] combos = getLetterCombinations( input );

		for(String combo : combos) 
			System.out.print(combo + ", ");
		
		System.out.println("\n");

		input = "235";
		combos = getLetterCombinations( input );

		for(String combo : combos) 
			System.out.print(combo + ", ");

	}


}









