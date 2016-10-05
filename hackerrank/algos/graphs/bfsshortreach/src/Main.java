import java.io.*;
import java.util.*;

public class Main {
  /**
   * Status: AC
   *
   * Topics: simple BFS.
   */

  public static void main(String[] args) throws FileNotFoundException {
    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;
    if (args.length > 0) {
      inputStream = new FileInputStream(args[0]);
    }
    InputReader in = new InputReader(inputStream);
    PrintWriter out = new PrintWriter(outputStream);

    int q = in.nextInt();
    while (q-- > 0) {
      Task solver = new Task();
      solver.solve(in, out);
    }
    out.close();
  }

  static class Task {
    int n, m, start;
    List<Integer>[] g;
    int[] d;
    boolean[] vst;

    void bfs(int start) {
      List<Integer> queue = new LinkedList<>();
      queue.add(start);
      d[start] = 0;
      vst[start] = true;
      while (!queue.isEmpty()) {
        int cur = queue.get(0);
        queue.remove(0);
        (g[cur]).stream().filter(nextV -> !vst[nextV]).forEach(nextV -> {
          vst[nextV] = true;
          d[nextV] = d[cur] + 6;
          queue.add(nextV);
        });
      }
    }

    public void solve(InputReader in, PrintWriter out) {
      n = in.nextInt();
      m = in.nextInt();
      g = new ArrayList[n];
      d = new int[n];
      Arrays.fill(d, -1);
      for (int i = 0; i < n; i++) {
        g[i] = new ArrayList<>();
      }
      for (int i = 0; i < m; i++) {
        int u = in.nextInt();
        int v = in.nextInt();
        u--;
        v--;
        g[u].add(v);
        g[v].add(u);
      }
      start = in.nextInt();
      start--;
      vst = new boolean[n];
      Arrays.fill(vst, false);
      bfs(start);
      boolean first = true;
      for (int i = 0; i < n; i++) {
        if (i != start) {
          if (first) {
            first = false;
          } else {
            out.print(" ");
          }
          out.print(d[i]);
        }
      }
      out.println("");
    }
  }

  static class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), 32768);
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }

  }
}
