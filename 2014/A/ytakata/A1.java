import java.util.Scanner;

class A1 {
  public static void main(String[] arg) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int x = scanner.nextInt(); // 前の税率
      int y = scanner.nextInt(); // 後の税率
      int s = scanner.nextInt(); // 前の税込み価格
      if (x == 0 && y == 0 && s == 0) break;
      System.out.println(solve(x, y, s));
    }
  }

  static int solve(int x, int y, int s) {
    int max = 0;
    for (int p1 = 1; p1 < s; p1++) { // 商品1の税抜き価格
      for (int p2 = p1; p2 < s; p2++) { // 商品2の税抜き価格(w.l.o.g. p1<=p2)
        int q1 = p1 * (100 + x) / 100;  // 商品1の前の税込み価格
        int q2 = p2 * (100 + x) / 100;  // 商品2の前の税込み価格
        if (q1 + q2 < s) continue;
        if (q1 + q2 > s) break;

        int r1 = p1 * (100 + y) / 100;  // 商品1の後の税込み価格
        int r2 = p2 * (100 + y) / 100;  // 商品2の後の税込み価格
        if (max < r1 + r2) {
            max = r1 + r2;
        }
      }
    }
    return max;
  }
}
