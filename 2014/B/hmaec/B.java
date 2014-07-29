import java.util.*;

public class B {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer>[] field;
		while (true) {
			int h = sc.nextInt();
			if (h == 0) {
				break;
			}
			field = new ArrayList[5];
			field[0] = new ArrayList();
			field[1] = new ArrayList();
			field[2] = new ArrayList();
			field[3] = new ArrayList();
			field[4] = new ArrayList();
			for (int i = 0; i < h; i++) {
				for (int j = 0 ; j < 5; j++) {
					int n = sc.nextInt();
					field[j].add(0, n);
				}
			}
			
			int score = 0;
			boolean flag;			
			do {
				flag = false;
				//System.out.println("+");
				for (int i = h - 1; i >= 0; i--) {
					//System.out.println("!");
					int[] tmp = new int[5];
					if (field[0].size() > i) { 
						tmp[0] = field[0].get(i);
					}
					if (field[1].size() > i) {
						tmp[1] = field[1].get(i);
					}
					if (field[2].size() > i) {
						tmp[2] = field[2].get(i);
					}
					if (field[3].size() > i) {
						tmp[3] = field[3].get(i);
					}
					if (field[4].size() > i) {
						tmp[4] = field[4].get(i);
					}
					if (tmp[0] == tmp[1] && tmp[1] == tmp[2] && tmp[2] == tmp[3] && tmp[3] == tmp[4] && tmp[0] != 0) {
						field[0].remove(i);
						field[1].remove(i);
						field[2].remove(i);
						field[3].remove(i);
						field[4].remove(i);
						score += tmp[0] * 5;
						flag = true;
					} else if (tmp[0] == tmp[1] && tmp[1] == tmp[2] && tmp[2] == tmp[3] && tmp[0] != 0) {
						field[0].remove(i);
						field[1].remove(i);
						field[2].remove(i);
						field[3].remove(i);
						score += tmp[0] * 4;
						flag = true;
					} else if (tmp[1] == tmp[2] && tmp[2] == tmp[3] && tmp[3] == tmp[4] && tmp[1] != 0) {
						field[1].remove(i);
						field[2].remove(i);
						field[3].remove(i);
						field[4].remove(i);
						score += tmp[1] * 4;
						flag = true;
					} else if (tmp[0] == tmp[1] && tmp[1] == tmp[2] && tmp[0] != 0) {
						field[0].remove(i);
						field[1].remove(i);
						field[2].remove(i);
						score += tmp[0] * 3;
						flag = true;
					} else if (tmp[1] == tmp[2] && tmp[2] == tmp[3] && tmp[1] != 0) {
						field[1].remove(i);
						field[2].remove(i);
						field[3].remove(i);
						score += tmp[1] * 3;
						flag = true;						
					} else if (tmp[2] == tmp[3] && tmp[3] == tmp[4] && tmp[2] != 0) {
						field[2].remove(i);
						field[3].remove(i);
						field[4].remove(i);
						score += tmp[2] * 3;
						flag = true;
					}
					
				}
			} while (flag);
			System.out.println(score);
			
			/*
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(field[j].get(i));
				}
				System.out.println();
			}
			System.out.println();
			*/
		}
	}
}
