import java.util.Scanner;

class C {
  public static void main(String[] arg) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int r = scanner.nextInt(); // 太陽の半径
      int n = scanner.nextInt(); // 建物数
      if (r == 0 && n == 0) break;
      initHeight();
      for (int i = 0; i < n; i++) {
        int x1 = scanner.nextInt(); // 区間の左端
        int x2 = scanner.nextInt(); // 区間の右端
        int h  = scanner.nextInt(); // 区間の高さ
        setHeight(x1, x2, h);
      }
      System.out.println(solve(r));
    }
  }

  final static int MIN_X = -20;
  final static int MAX_X =  20;

  static double solve(int r) {
    double min = height(0); // 区間[0,1]から太陽が現れるまでの時間
    for (int x = -r; x < r; x++) {
      int x0 = (x < 0 ? x + 1 : x); // x, x+1のうち0に近い方
      double y = Math.sqrt(r * r - x0 * x0) - r; // x0における太陽の上端
      double t = height(x) - y; // 区間[x,x+1]から太陽が現れるまでの時間
      if (min > t) min = t;
    }
    return min;
  }

  // 各区間[x,x+1]について, x < 0 なら区間の右端(x+1)から太陽が現れる.
  // x >= 0 なら左端(x)から太陽が現れる.

  /** 区間[x,x+1]における高さを返す */
  static int height(int x) { return height[x - MIN_X]; }

  /** 区間[x1,x2]における高さをセット */
  static void setHeight(int x1, int x2, int h) {
    for (int x = x1; x < x2; x++) {
      if (height[x - MIN_X] < h) height[x - MIN_X] = h;
    }
  }

  static int[] height = new int[MAX_X - MIN_X];

  static void initHeight() {
    for (int i = 0; i < height.length; i++) height[i] = 0;
  }
}
