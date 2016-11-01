import java.util.*;

public class Template {
  public static void main(String[] args) {
    Scanner inp = new Scanner(System.in);
    long a = inp.nextInt();
    long b = inp.nextInt();
    long c = inp.nextInt();
    long a1 = (a + c - 1) / c;
    long b1 = (b + c - 1) / c;
    System.out.println(a1 * b1);
  }
}
