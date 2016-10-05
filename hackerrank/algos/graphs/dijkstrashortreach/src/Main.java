import java.io.*;
import java.util.*;

public class Main {
  /**
   * Status: AC
   *
   * Topics: Dijkstra and fast java IO.
   */

  public static void main(String[] args) throws FileNotFoundException {
    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;
    if (args.length > 0) {
      inputStream = new FileInputStream(args[0]);
    }
    InputReader in = new InputReader(inputStream);
    PrintWriter out = new PrintWriter(outputStream);

    int nt = in.nextInt();
    while (nt-- > 0) {
      Task solver = new Task();
      solver.solve(in, out);
    }
    out.close();
  }

  static class Edge {
    public int v, c;
    public Edge(int v, int c) {
      this.v = v;
      this.c = c;
    }
  }

  static class Node implements Comparable<Node> {
    public int v, c;
    public Node(int v, int c) {
      this.v = v;
      this.c = c;
    }

    @Override
    public int compareTo(Node other) {
      if (c == other.c) return 0;
      return c > other.c ? +1 : -1;
    }
  }

  static class Task {
    int n, m;
    PriorityQueue<Node> pq = new PriorityQueue<>();
    List<Edge>[] g;
    int[] d;
    boolean[] vst;

    public void solve(InputReader in, PrintWriter out) {
      n = in.nextInt();
      m = in.nextInt();
      g = new ArrayList[n];
      for (int i = 0; i < n; i++) {
        g[i] = new ArrayList<>();
      }
      for (int i = 0; i < m; i++) {
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        x--;
        y--;
        g[x].add(new Edge(y, z));
        g[y].add(new Edge(x, z));
      }
      int start = in.nextInt();
      start--;
      d = new int[n];
      Arrays.fill(d, -1);

      vst = new boolean[n];
      Arrays.fill(vst, false);

      boolean updated = true;
      d[start] = 0;
      while(true) {
        int minDist = Integer.MAX_VALUE;
        int bestIdx = -1;
        for (int i = 0; i < n; i++) {
          if (d[i] != -1 && !vst[i] && minDist > d[i]) {
            minDist = d[i];
            bestIdx = i;
          }
        }
        //out.println("bestIdx:" + bestIdx);
        if (bestIdx == -1) {
          break;
        }
        vst[bestIdx] = true;

        for (Edge e : g[bestIdx]) {
          if (vst[e.v]) {
            continue;
          }
          if (d[e.v] == -1 || d[e.v] > minDist + e.c) {
            d[e.v] = minDist + e.c;
          }
        }
      }

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
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
      this.stream = stream;
    }

    public int read() {
      if (numChars == -1)
        throw new InputMismatchException();
      if (curChar >= numChars) {
        curChar = 0;
        try {
          numChars = stream.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }
        if (numChars <= 0)
          return -1;
      }
      return buf[curChar++];
    }

    public int nextInt() {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      int res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public String readString() {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = read();
      } while (!isSpaceChar(c));
      return res.toString();
    }

    public boolean isSpaceChar(int c) {
      if (filter != null)
        return filter.isSpaceChar(c);
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
      return readString();
    }

    public interface SpaceCharFilter {
      public boolean isSpaceChar(int ch);
    }

  }
}
