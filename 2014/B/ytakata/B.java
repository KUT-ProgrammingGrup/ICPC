import java.util.Scanner;

class B {
  public static void main(String[] arg) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int h = scanner.nextInt(); // 盤面の高さ
      if (h == 0) break;
      board = new int[h][5];
      for (int y = 0; y < h; y++) {
        for (int x = 0; x < board[y].length; x++) {
          board[y][x] = scanner.nextInt(); // 石
        }
      }
      System.out.println(solve());
    }
  }

  static int[][] board;  // 盤面

  static int solve() {
    int score = 0;
    while (true) {
      int s = eraseStone();   // 石を消す
      if (s == 0) break; // どこも消せなかったら終了
      score += s;
      dropDown();        // 石を下に落とす
    }
    return score;
  }

  /** 石を消し, 得点を返す */
  static int eraseStone() {
    int score = 0;
    for (int y = 0; y < board.length; y++) {
      score += eraseStoneInRow(board[y]); // 1行分
    }
    return score;
  }

  /** 1行分について, 石を消し, 得点を返す */
  static int eraseStoneInRow(int[] row) {
    int score = 0;
    // 並んでいる個数を数える
    int head = row.length - 1; // 同じ石の列の開始位置(初期値: 右端)
    int len  = 1; // 同じ石の列の長さ
    int l = 1;
    for (int x = row.length - 2; x >= 0; x--) {
      l = (row[x] == row[x + 1] ? l + 1 : 1); // 右隣と同じ石なら1増やす
      if (len < l) {
        len  = l;
        head = x;
      }
    }
    // 3個以上並んでいるなら消す
    if (len >= 3) {
      for (int x = head; x < head + len; x++) {
        score += row[x];
        row[x] = 0; // 石を消す
      }
    }
    return score;
  }

  /** 石を下に落とす */
  static void dropDown() {
    for (int x = 0; x < board[0].length; x++) {
      int yto = board.length - 1;
      for (int y = board.length - 1; y >= 0; y--) {
        if (board[y][x] > 0) {
          board[yto--][x] = board[y][x];
        }
      }
    }
  }
}
