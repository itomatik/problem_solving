import java.util.*;

public class Main {


  public static void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  // Computes lexicographically next permutation.
  // Works even if you have repeated elements in your array.
  public static int nextPermutation(int[] a) {
    int N = a.length;
    int i = N - 1;
    // find longest non increasing suffix.
    while (i > 0 && !(a[i - 1] < a[i]))i--;

    // we're done. this is last permutation.
    if (i == 0) return -1;

    // pivot element is a[i - 1] - this is the element we want to swap out with something from the suffix.
    // we want to swap it out with smallest element that bigger than the pivot.

    // find rightmost element that is bigger than the pivot
    int j = i;
    while(j < N && a[i - 1] < a[j])j++;
    j--;

    // swap pivot and its successort
    swap(a, i - 1, j);

    // reverse the suffix, i.e. make it sorted. this gives us smallest next permutation after we swap out the pivot.
    j = N - 1;
    while (i < j) {
      swap(a, i++, j--);
    }
    return 1;
  }

  public static void print(int[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] a = new int[] { 0, 0, 1, 2, 2, 3};

    for (int i = 0; i < 180; i++) {
      print(a);
      nextPermutation(a);
    }
  }
}
