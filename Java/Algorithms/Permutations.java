import java.util.List;
import java.util.LinkedList;

class Permutations {

	static List< List<Character> > generatePermutations( List<Character> items) {
		List< List<Character> > permutations = new LinkedList<>();
		permutations.add(new LinkedList<>());

		for(int i = 0; i < items.size(); i++) {
			char currChar = items.get(i);
			while(permutations.get(0).size() < i + 1) {
				List<Character> part = permutations.remove(0);
				for (int j = 0; j <= part.size(); j++) {
					List<Character> newPerm = new LinkedList<>(part);
					newPerm.add(j, currChar);
					permutations.add(newPerm);
				}
			}
		}

		return permutations;
	}

	public static void main(String[] args) {

		List<Character> set = new LinkedList<>();						// set of items to permute
		for(Character c : (new String("12345")).toCharArray() ) 			
			set.add( c );

		int i = 0;
		for(List<Character> list : generatePermutations( set ) ) {		// 5! = 120 permutations
			System.out.print("\n" + ++i + ": ");
			for(char c : list) 
				System.out.print(c);
		}

	}
}