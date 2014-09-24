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
			char[] sca = s.toCharArray();
			//System.out.println(new String(sca));
			ans = 0;
			decode(sca, 0);
			//encode();
			System.out.println(ans);
			for (int i = 0; i < sArray.size(); i++) {
				System.out.println(sArray.get(i));
			}
		}
	}
	
	public static void decode(char[] sca, int pos) {
		//System.out.println(new String(sca) + " " + sca[0] + " " + pos + " " + sca.length);
		if (pos == sca.length) {
			if (encode(sca)) {
				ans++;
				sArray.add(new String(sca));
				Collections.sort(sArray);
				if (sArray.size() > 10) {
					sArray.remove(5);
				}
			}
			return;
		}
		decode(sca, pos + 1);
		if (sca[pos] != 'z') {
			//System.out.println(sca[pos]);
			sca[pos]++;
			decode(sca, pos + 1);
			sca[pos]--;
		}
	}
	
	public static boolean encode(char[] sca) {
		char[] tmp = Arrays.copyOf(sca, sca.length);
		for (int i = 1; i <= 25; i++) {
			char c = (char)('a' + i);
			for (int j = 0; j < tmp.length; j++) {
				if (tmp[j] == c) {
					tmp[j]--;
					break;
				}
			}
		}
		//System.out.println(sca);
		if (s.equals(new String(tmp))) {
			return true;
		} else {
			return false;
		}
	}
}
