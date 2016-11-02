import java.util.*;

public class Main {

  public Set<Integer> primes = new HashSet<>();

  HashMap<List<Integer>, Double> memo = new HashMap<>();

  double rec(int x, int y, int step, int A, int B) {
    if (memo.containsKey(Arrays.asList(x, y, step))) {
      return memo.get(Arrays.asList(x, y, step));
    }
    if (step == 18) {
      if (primes.contains(x) || primes.contains(y)) {
        return 1;
      } else {
        return 0;
      }
    }
    double ret = 
        A / 100.0 * (100.0 - B) / 100.0 * rec(x + 1, y, step + 1, A, B)
      + B / 100.0 * (100.0 - A) / 100.0 * rec(x, y + 1, step + 1, A, B)
      + A / 100.0 * B / 100.0 * rec(x + 1, y + 1, step + 1, A, B)
      + (100.0 - A) / 100.0 * (100.0 - B) / 100.0 * rec(x, y, step + 1, A, B);
    memo.put(Arrays.asList(x, y, step), ret);
    return ret;
  }

  double getProbability(int A, int B) {
    return rec(0, 0, 0, A, B);
  }

  public static void main(String[] args) {
    Main m = new Main();
    m.primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
    Scanner inp = new Scanner(System.in);
    int a = inp.nextInt();
    int b = inp.nextInt();
    System.out.println(m.getProbability(a, b));
  }
}
