import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Permutations {

    // DFS
    List<List<Integer>> permutations_dfs1(List<Integer> list) {
        List<List<Integer>> perms = new LinkedList<>();
        dfs1(perms, list, 0);
        return perms;
    }

    void dfs1(List<List<Integer>> permutations, List<Integer> orig, int index) {
        if(index == orig.size())
            permutations.add(new LinkedList<>(orig));
        else
            for (int i = index; i < orig.size(); i++) {
                swap(orig, index, i);
                dfs1(permutations, orig, index + 1);
                swap(orig, index, i);
            }
    }

    void swap(List<Integer> list, int a, int b) {
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

    // --------------------------------------------------------------------------------

	// DFS
    List<List<Integer>> permutations_dfs2(List<Integer> list) {
        List<List<Integer>> perms = new LinkedList<>();
        dfs2(perms, list, new ArrayList<>(), 0);
        return perms;
    }

    void dfs2(List<List<Integer>> permutations, List<Integer> orig, List<Integer> curr, int index) {
        if(index == orig.size())
            permutations.add(new ArrayList<>(curr));
        else
            for (int i = 0; i <= curr.size(); i++) {
                curr.add(i, orig.get(index));
                dfs2(permutations, orig, curr, index + 1);
                curr.remove(i);
            }
    }

    // --------------------------------------------------------------------------------

	// BFS
    List<List<Integer>> permutations_bfs(List<Integer> list) {
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

    // --------------------------------------------------------------------------------

	public static void main(String[] args) {

		List<Integer> items = new LinkedList<>();						// list of items to permute
		for(int c : new int[]{1, 2, 3, 4, 5})                           // 5! = 120 permutations
            items.add( c );

         System.out.println( (new Permutations()).permutations_bfs(items).size() ); 
         System.out.println( (new Permutations()).permutations_dfs1(items).size() ); 
         System.out.println( (new Permutations()).permutations_dfs2(items).size() ); 
	}
}