import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

class Permutations {

	// DFS / Backtracking
    static List<List<Integer>> permutations_dfs(List<Integer> list) {
        List<List<Integer>> perms = new LinkedList<>();
        dfs(perms, list, new LinkedList<>(), 0);
        return perms;
    }

    static void dfs(List<List<Integer>> permutations, List<Integer> orig, List<Integer> curr, int index) {
        if(index == orig.size())
            permutations.add(new LinkedList<>(curr));
        else
            for (int i = 0; i <= curr.size(); i++) {
                curr.add(i, orig.get(index));
                dfs(permutations, orig, curr, index + 1);
                curr.remove(i);
            }
    }

	// BFS
    static List<List<Integer>> permutations_bfs(List<Integer> list) {
        Queue<List<Integer>> q = new LinkedList<>();
        q.add(new LinkedList<>());

        for(int n : list) {
            int size = q.size();
            while (size-- > 0) {
                List<Integer> lis = q.poll();
                for (int i = 0; i <= lis.size(); i++) {
                    List<Integer> part = new LinkedList<>(lis);
                    part.add(i, n);
                    q.add(part);
                }
            }
        }

        return new LinkedList<>(q);
    }

	public static void main(String[] args) {

		List<Integer> items = new LinkedList<>();						// list of items to permute
		for(Integer c : new int[]{1, 2, 3, 4, 5}) 			
			items.add( c );

		int i = 0;
		for(List<Integer> list : permutations_dfs( items ) ) {		// 5! = 120 permutations
			System.out.print("\n" + ++i + ": ");
			for(int c : list) 
				System.out.print(c);
		}

	}
}