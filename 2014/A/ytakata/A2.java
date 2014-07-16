import java.util.Scanner;

class A2 {
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
      int q1 = p1 * (100 + x) / 100; // 商品1の前の税込み価格
      int r1 = p1 * (100 + y) / 100; // 商品1の後の税込み価格
      int q2 = s - q1;               // 商品2の前の税込み価格
      int p2 = (q2 * 100 + 99) / (100 + x); // 商品2の税抜き価格
      if (p1 > p2) break; // 商品1,2は対称なので, p1<=p2の範囲のみ探せば十分
      if (p2 * (100 + x) / 100 != q2) continue; // ちょうどq2円になるp2がない
      int r2 = p2 * (100 + y) / 100; // 商品2の後の税込み価格
      if (max < r1 + r2) {
          max = r1 + r2;
      }
    }
    return max;
  }

  /*
   * 税込み価格q2から税抜き価格p2を求める方法:
   *   floor(p2 * (100 + x) / 100) = q2
   * であるので, 余りを r (ただし 0 <= r <= 99) とすると
   *   p2 = (q2 * 100 + r) / (100 + x).
   * r=0のときのp2が下限，r=99のときのp2が上限.
   * 0.0 <= r / (100 + x) < 1.0 より, 上記の範囲にある整数p2は高々1つなので,
   *   p2 = ceil(q2 * 100 / (100 + x))
   * または
   *   p2 = floor((q2 * 100 + 99) / (100 + x))
   * の一方のみ調べれば十分 (上記のプログラムでは後者のみ調べている).
   *
   * なお, ちょうどq2になるp2が存在しない場合もある.
   * 例えば, q2=299円, 税率99%のとき, 150円->298円, 151円->300円.
   */

}
