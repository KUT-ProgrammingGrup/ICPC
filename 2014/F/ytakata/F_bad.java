import java.util.*;

/**
 * 計算量的にだめだめな解答 (全探索).
 * 本番データどころかSample Inputでもだめ
 *  (Sample Inputの最初の6セットだけならうまくいくが).
 */
class F {
  public static void main(String[] arg) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int[] target = new int[6];
      int total = 0;
      for (int i = 0; i < 6; i++) {
        target[i] = scanner.nextInt(); // 目標値
        total += target[i]; // 目標値の総和
      }
      if (total == 0) break;
      int p = scanner.nextInt();
      int q = scanner.nextInt();

      dice = new Dice(target);
      candidate = new char[total];
      boolean found = search(0); // 解を探索
      if (! found) {
        System.out.println("impossible");
      } else {
        for (int i = p - 1; i < q; i++) {
          System.out.print(candidate[i]);
        }
        System.out.println();
      }
    }
  }

  static char[] candidate; // 構築中の解候補
  static Dice dice; // さいころ

  final static char[] letter = { 'E', 'N', 'S', 'W' };

  /**
   * 解を探索する.
   * @param pos 構築済みの解の長さ 
   * @return 解が見つかったときかつそのときのみ<code>true</code>
   */
  static boolean search(int pos) {
    if (pos >= candidate.length) { // 解を発見
      return true;
    }
    // E, N, S, Wの順に試す
    for (int d = 0; d < 4; d++) {
      candidate[pos] = letter[d];
      dice.turn(letter[d]);
      if (dice.consistent()) {
        boolean found = search(pos + 1);
        if (found) return true;
      }
      dice.rollback(letter[d]);
    }
    return false;
  }
}

class Dice {
  int[] face = new int[6]; // 0の裏は5, 1の裏は4, 2の裏は3.
  int top   = 0; // 上面
  int south = 1; // 南面
  int west  = 2; // 西面

  int[] target; // 目標値

  Dice(int[] target) {
    this.target = target;
  }

  static int opp(int index) { return 5 - index; }

  void turn(char d) {
    int tmp;
    switch (d) {
    case 'E': tmp = west;       west  = opp(top); top = tmp; break;
    case 'W': tmp = opp(west);  west  = top;      top = tmp; break;
    case 'N': tmp = south;      south = opp(top); top = tmp; break;
    case 'S': tmp = opp(south); south = top;      top = tmp; break;
    }
    face[opp(top)]++;
  }
  void rollback(char d) {
    face[opp(top)]--;
    int tmp;
    switch (d) {
    case 'W': tmp = west;       west  = opp(top); top = tmp; break;
    case 'E': tmp = opp(west);  west  = top;      top = tmp; break;
    case 'S': tmp = south;      south = opp(top); top = tmp; break;
    case 'N': tmp = opp(south); south = top;      top = tmp; break;
    }
  }

  /**
   * 目標値と矛盾しないか
   */
  boolean consistent() {
    int[] copy = Arrays.copyOf(face, face.length);
    Arrays.sort(copy);
    for (int i = 0; i < target.length; i++) {
      if (target[i] < copy[i]) { // 目標値を超えていたら矛盾
        return false;
      }
    }
    return true;
  }
}
