import java.util.*;

public class D {
	static int ans;
	static String s;
	static ArrayList<String> sArray;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			s = sc.next();
			if (s.equals("#")) {
				break;
			}
			sArray = new ArrayList<String>();
			ans = 0;
			decode(s, s.length() - 1);
			//encode();
			Collections.sort(sArray);
			System.out.println(ans);
			if (ans > 0) {
				if (10 >= ans) {
					for (int i = 0; i < sArray.size(); i++) {
						System.out.println(sArray.get(i));
					}
				} else {
					for (int i = 0; i < 5; i++) {
						System.out.println(sArray.get(i));
					}
					for (int i = 5; i > 0; i--) {
						System.out.println(sArray.get(sArray.size() - i));
					}
				}
			}
		}
	}
	
	public static void decode(String sa, int pos) {
		StringBuffer sb = new StringBuffer(sa);
		if (pos == -1) {
			if (encode(sb.toString())) {
				ans++;
				sArray.add(sb.toString());
			}
			return;
		}
		decode(sb.toString(), pos - 1);
		if (sb.charAt(pos) != 'z') {
			sb.setCharAt(pos, (char)(sb.charAt(pos) + 1));
			decode(sb.toString(), pos - 1);
		}
	}
	
	public static boolean encode(String sa) {
		StringBuffer sb = new StringBuffer(sa);
		for (int i = 1; i <= 25; i++) {
			char c = (char)('a' + i);
			String tmp = Character.toString(c);
			//System.out.println(tmp);
			int pos = sb.indexOf(tmp);
			if (pos != -1) {
				sb.setCharAt(pos, (char)(c - 1));
			}
		}
		//System.out.println(sb);
		if (s.equals(sb.toString())) {
			return true;
		} else {
			return false;
		}
	}
}
