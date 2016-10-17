import java.io.*;
import java.util.*;

public class Solution {
  public static final long MOD = 1000000007;

  int n, m;
  public Solution(int n, int m) {
    this.n = n;
    this.m = m;
  }

  public int solve() {
    long[] d = new long[m + 2];
    d[0] = 1;
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= 4; j++) {
        if (i >= j) d[i] = (d[i] + d[i - j]) % MOD;
      }
    }
    long[][] total = new long[n + 2][];
    for (int i = 0; i <= n; i++) {
      total[i] = new long[m + 2];
    }
    for (int j = 1; j <= m; j++) {
      total[0][j] = 1L;
      for (int i = 1; i <= n; i++) {
        total[i][j] = (total[i - 1][j] * d[j]) % MOD;
      }
    }

    long result = total[n][m];

    long[] dp = new long[m + 2];
    dp[0] = 1L;
    dp[1] = 1L;

    for (int j = 2; j <= m; j++) {
      dp[j] = total[n][j];
      for (int split = 1; split <= j; split++) {
        if (split < j) {
          dp[j] = (MOD + dp[j] - (dp[split] * total[n][j - split])% MOD)%MOD;
        }
      }
    }

    result = dp[m];
    return (int)result;
  }

  public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for (int test = 0; test < T; test++) {
      int n = sc.nextInt();
      int m = sc.nextInt();
      Solution solver = new Solution(n, m);
      System.out.println(solver.solve());
    }
  }
}
