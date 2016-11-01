import java.util.*;

public class Main {
  /*
   * Maximum / minimum subsum problem variation
   */
  public static final int MAXN = 10002;
  int N;
  int[] D;
  long res;
  long[] min_left;
  long[] max_left;
  long[] min_right;
  long[] max_right;


  long[] sub_min_left;
  long[] sub_max_left;
  long[] sub_min_right;
  long[] sub_max_right;

  public Main() {
    D = new int[10002];
    min_left = new long[MAXN];
    max_left = new long[MAXN];
    min_right = new long[MAXN];
    max_right = new long[MAXN];
    sub_min_left = new long[MAXN];
    sub_max_left = new long[MAXN];
    sub_min_right = new long[MAXN];
    sub_max_right = new long[MAXN];
  }

  public void readData(Scanner inp) {
    N = inp.nextInt();
    for (int i = 0; i < N; i++) {
      D[i] = inp.nextInt();
    }

    Arrays.fill(min_left, Long.MAX_VALUE);
    Arrays.fill(max_left, Long.MIN_VALUE);
    Arrays.fill(min_right, Long.MAX_VALUE);
    Arrays.fill(max_right, Long.MIN_VALUE);

    Arrays.fill(sub_min_left, Long.MAX_VALUE);
    Arrays.fill(sub_max_left, Long.MIN_VALUE);
    Arrays.fill(sub_min_right, Long.MAX_VALUE);
    Arrays.fill(sub_max_right, Long.MIN_VALUE);
  }

  public void solve() {
    // left
    min_left[0] = D[0];
    max_left[0] = D[0];
    sub_min_left[0] = D[0];
    sub_max_left[0] = D[0];
    for (int i = 1; i < N; i++) {
      min_left[i] = Math.min(min_left[i - 1] + D[i], D[i]);
      max_left[i] = Math.max(max_left[i - 1] + D[i], D[i]);
      sub_min_left[i] = Math.min(min_left[i - 1] + D[i], Math.min(sub_min_left[i - 1], D[i]));
      sub_max_left[i] = Math.max(max_left[i - 1] + D[i], Math.max(sub_max_left[i - 1], D[i]));
    }

    // right
    min_right[N - 1] = D[N - 1];
    max_right[N - 1] = D[N - 1];
    sub_min_right[N - 1] = D[N - 1];
    sub_max_right[N - 1] = D[N - 1];
    for (int i = N - 2; i >= 0; i--) {
      min_right[i] = Math.min(min_right[i + 1] + D[i], D[i]);
      max_right[i] = Math.max(max_right[i + 1] + D[i], D[i]);
      sub_min_right[i] = Math.min(min_right[i + 1] + D[i], Math.min(sub_min_right[i + 1], D[i]));
      sub_max_right[i] = Math.max(max_right[i + 1] + D[i], Math.max(sub_max_right[i + 1], D[i]));
    }

    res = 0;
    for (int i = 0; i < N - 1; i++) {
      long tmp1 = Math.abs(sub_min_left[i] - sub_min_right[i + 1]);
      long tmp2 = Math.abs(sub_min_left[i] - sub_max_right[i + 1]); 
      long tmp3 = Math.abs(sub_max_left[i] - sub_min_right[i + 1]); 
      long tmp4 = Math.abs(sub_max_left[i] - sub_max_right[i + 1]); 

      res = Math.max(res,
          Math.max(tmp1,
            Math.max(tmp2,
              Math.max(tmp3, tmp4))));
    }
  }

  public void doit() {
    solve();
    System.out.println(res);
  }

  public static void main(String[] args) {
    Scanner inp = new Scanner(System.in);
    Main s = new Main();
    int T = inp.nextInt();
    while(T-->0){ 
      s.readData(inp);
      s.doit();
    }
  }
}
