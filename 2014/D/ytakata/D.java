import java.util.*;

class D {
  public static void main(String[] arg) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String cipher = scanner.next(); // 暗号文
      if (cipher.equals("#")) break;
      Set<String> set = decrypt(cipher); // 解集合
      int n = set.size(); // 解の個数
      System.out.println(n);
      int i = 0;
      for (String s : set) {
        if (i < 5 || n - 5 <= i) { // 最初と最後の5個ずつ
          System.out.println(s);
        }
        i++;
      }
    }
  }

  /** 'z', 'y', ..., 'b' と順に復号する */
  static Set<String> decrypt(String cipher) {
    Set<String> set = new TreeSet<String>();
    set.add(cipher);
    for (char c = 'z'; c >= 'b'; c--) {
      set = decrypt(set, c);
    }
    return set;
  }

  /** s ∈ set に対する decrypt(s, c) の総和を返す */
  static Set<String> decrypt(Set<String> set, char c) {
    Set<String> result = new TreeSet<String>();
    for (String s : set) {
      result.addAll(decrypt(s, c));
    }
    return result;
  }

  /**
   * 集合 { p | crypt(p, c) == cipher } を返す.
   * crypt(p, c) は, p中の最初の c を c-1 に換える.
   */
  static Set<String> decrypt(String cipher, char c) {
    Set<String> result = new TreeSet<String>();

    // cipher自身が平文と等しい場合
    if (crypt(cipher, c).equals(cipher)) { result.add(cipher); }

    for (int i = 0; i < cipher.length(); i++) {
      if (cipher.charAt(i) == c - 1) {
        // c-1 を c に置き換えた文字列pが平文候補.
        // pを暗号化したらcipherになるなら, pを解集合に追加.
        String p = cipher.substring(0, i) + c + cipher.substring(i + 1);
        if (crypt(p, c).equals(cipher)) { result.add(p); }
      }
    }
    return result;
  }

  /** p中の最初の c を c-1 に換えた文字列を返す */
  static String crypt(String p, char c) {
    int i = p.indexOf(c);
    return i < 0 ? p : p.substring(0, i) + (char)(c - 1) + p.substring(i + 1);
  }
}
