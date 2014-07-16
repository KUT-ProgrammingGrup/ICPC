import java.util.*;

class E {
  public static void main(String[] arg) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int n = scanner.nextInt(); // 島の数
      if (n == 0) break;
      parent = new int[n];
      d      = new int[n];
      for (int i = 1; i < n; i++) {
        parent[i] = scanner.nextInt()-1; // 親島(0..n-1)
      }
      for (int i = 1; i < n; i++) {
        d[i] = scanner.nextInt(); // 親島までの橋の長さ
      }
      System.out.println(solve());
    }
  }

  static int[] parent; // 親島
  static int[] d;      // 親島までの橋の長さ

  static int solve() {
    int n = parent.length; // 頂点数

    // 各頂点の次数を計算
    int[] degree = new int[n];
    for (int i = 1; i < n; i++) {
      degree[parent[i]]++;
      degree[i]++;
    }

    // 全辺の和totalと, 次数1の頂点に接続する辺の和pendantを計算
    int total   = 0;
    int pendant = 0;
    for (int i = 1; i < n; i++) {
      total += d[i];
      if (degree[i] <= 1 || degree[parent[i]] <= 1) {
        pendant += d[i];
      }
    }

    // iからjまでの距離dist[i][j]を計算 (i >= jの範囲のみ計算)
    int[][] dist = new int[n][n];
    int maxDist = 0;
    for (int s = 0; s < n; s++) {
      int p = parent[s];
      for (int t = 0; t < s; t++) {
        // sからtまでの距離 = sからpまでの距離 + pからtまでの距離.
        // sからpまでの距離 = d[s].
        // p < sかつt < sなので, dist[p][t]またはdist[t][p]の一方は計算済み
        int dpt = (p > t ? dist[p][t] : dist[t][p]); // pからtまでの距離
        dist[s][t] = d[s] + dpt;
        if (degree[s] <= 1 || degree[t] <= 1) continue;
        if (maxDist < dist[s][t]) { // 次数2以上の頂点間の最大距離
            maxDist = dist[s][t];
        }
      }
    }

    /*
     * (移動時間を除いて)橋の撤去にかかる時間 = total (撤去の順序には無関係).
     * 撤去作業の開始頂点をs, 終了頂点をtとすると, 
     *   s, tは次数2以上の頂点のみ考慮すればよい.
     *   sからtまでのパス上の辺 (maxDist) はちょうど1回通過.
     *   次数1の頂点に接続する辺 (pendant) は通らなくてよい.
     *   それ以外の辺 (total - pendant - maxDist) はちょうど2回通過(往復).
     */
    return total + maxDist + 2 * (total - pendant - maxDist);
  }

}
