import java.util.*;

/**
 * gyaneの解法はまちがってなかったんでないかい?
 * という確認のためのコード.
 * D.java (逆像を計算する) より簡潔だし速いね.
 */
class D2 {
  public static void main(String[] arg) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String cipher = scanner.next(); // 暗号文
      if (cipher.equals("#")) break;

      result = new ArrayList<String>();
      search(cipher, 0, ""); // 解を探索

      int n = result.size(); // 解の個数
      System.out.println(n);
      int i = 0;
      for (String s : result) {
        if (i < 5 || n - 5 <= i) { // 最初と最後の5個ずつ
          System.out.println(s);
        }
        i++;
      }
    }
  }

  static List<String> result;

  /**
   * 解を探索する.
   * @param cipher 暗号文 (gyaneコードではbuf)
   * @param pos    これから解読する位置 (gyaneコードではcount)
   * @oaram candidate 現在構築中の解候補 (gyaneコードではconverted)
   */
  static void search(String cipher, int pos, String candidate) {
    if (pos >= cipher.length()) { // 解を発見
      result.add(candidate);
      return;
    }

    char c = cipher.charAt(pos);
    if (c == 'a' || candidate.indexOf(c) >= 0) { // cはすでに出現している
      search(cipher, pos + 1, candidate + c);
    }
    if (c != 'z' && candidate.indexOf(c + 1) < 0) { // c+1はまだ出現していない
      search(cipher, pos + 1, candidate + (char)(c + 1));
    }
  }
}
