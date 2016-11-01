import java.util.*;

/*
 * Spoj PT07Z - Longest path in a tree
 * Simple DFS and observations in the tree.
 */
public class Main {
  int N;
  HashMap<Integer, List<Integer>> t;
  Set<Integer> vst;
  int res = 1;

  int dfs(int u) {
    vst.add(u);
    int mx1 = 0, mx2 = 0;

    if (!t.containsKey(u)) {
      return 1;
    }

    for (int v : t.get(u)) {
      if (!vst.contains(v)) {
        int tmp = dfs(v);
        if (mx1 == 0) {
          mx1 = tmp;
        } else if (mx1 < tmp) {
          mx2 = mx1;
          mx1 = tmp;
        } else if (mx2 < tmp) {
          mx2 = tmp;
        }
      }
    }

    res = Math.max(res, mx1 + mx2 + 1);
    //System.out.println("u:" + (u + 1) + " mx1:" + mx1 + " mx2:" + mx2 + " res:" + res);

    return mx1 + 1;
  }

  void addEdge(int u, int v) {
    if (!t.containsKey(u)) {
      t.put(u, new ArrayList<>());
    }
    t.get(u).add(v);
  }

  void readData(Scanner inp) {
    t = new HashMap<>();
    vst = new HashSet<>();
    N = inp.nextInt();
    for (int i = 0; i < N - 1; i++) {
      int u = inp.nextInt();
      int v = inp.nextInt();
      u--;
      v--;
      addEdge(u, v);
      addEdge(v, u);
    }
  }

  void doit() {
    for (int i = 0; i < N; i++) {
      if (!vst.contains(i)) 
        dfs(i);
    }
    System.out.println(res - 1);
  }

  public static void main(String[] args) {
    Scanner inp = new Scanner(System.in);
    Main s = new Main();
    s.readData(inp);
    s.doit();
  }
}
