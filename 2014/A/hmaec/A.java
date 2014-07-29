import java.util.*;

public class A {
	static int[] price;
	static int[] price2;
	static int x;
	static int y;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		price = new int[1002];
		price2 = new int[1002];
		while (true) {
			x = sc.nextInt();
			y = sc.nextInt();
			int s = sc.nextInt();
			if (x == 0) {
				break;
			}
			aaa();
			//int ns = (int)(s / ((double)(100 + x) / 100));
			int max = 0;
			for (int i = 1; i < s; i++) {
				int j = s - i;
				int ni = Arrays.binarySearch(price, i);
				int nj = Arrays.binarySearch(price, j);
				if (ni <= 0 || nj <= 0) {
					continue;
				}
				//int ni = (int)(((double)i / ((double)(100 + x) / 100)) * 10 + 9) / 10;
				//int nj = (double)j / (100 + x / 100);
				//int nj = (int)(((double)j / ((double)(100 + x) / 100)) * 10 + 9) / 10;
				//int ni = (int)(i / ((double)(100 + x) / 100));
				//int nj = (int)(j / ((double)(100 + x) / 100));
				//System.out.println(ni + " " + nj);
				int newi = ni * (100 + y) / 100;
				int newj = nj * (100 + y) / 100;
				if (max < newi + newj) {
					max = newi + newj;
				}
			}
			System.out.println(max);
		}
	}
	
	public static void aaa() {
		for (int i = 1; i < 1002; i++) {
			price[i] = i * (100 + x) / 100;
			price2[i] = i * (100 + y) / 100;
		}
	}
}
