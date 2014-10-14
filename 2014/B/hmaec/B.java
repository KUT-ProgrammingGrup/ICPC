import java.util.Scanner;

public class B {
    static int sum; // 消した石の数値の合計
    public static void main(String[] args) {
        final int ROW = 5; // 列の数
        final int MINREMOVE = 3; // 消すのに必要な個数
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int H = sc.nextInt();
            if (H == 0) {
                break;
            }
            
            int[][] field = new int[H][ROW+1];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < ROW; j++) {
                    field[i][j] = sc.nextInt();
                }
                field[i][ROW] = 0;
            }
            sum = 0;
            
            //printField(field);
            //System.out.println();
            while (removeField(field, MINREMOVE)) {
                //printField(field);
                //System.out.println();
                fallField(field);
                //printField(field);
                //System.out.println();
            }
            
            System.out.println(sum);
        }
    }
    
    public static boolean removeField(int[][] field, int n) {
        boolean isRemove = false; // 1回以上消したかどうか
        
        for (int i = 0; i < field.length; i++) {
            int num = 0; // num連続
            for (int j = 0; j < field[i].length; j++) {
                if (j == 0) {
                    num = 1;
                } else {
                    //System.out.println(i + " " + j);
                    if (field[i][j-1] == field[i][j] && field[i][j] != 0) {
                        num++;
                    } else {
                        if (num >= n) {
                            removeStone(field, j - num, i, num);
                            isRemove = true;
                        }
                        num = 1;
                    }
                }
            }
        }
        return isRemove;
    }
    
    public static void removeStone(int[][] field, int x, int y, int n) {
        for (int i = 0; i < n; i++) {
            sum += field[y][x+i];
            field[y][x+i] = 0;
        }
    }
    
    public static void fallField(int[][] field) {
        // 下から2段目以上にある石を落とす
        for (int i = field.length - 2; i >= 0; i--) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] != 0) {
                    int x = j;
                    int y = i;
                    while (y < field.length - 1&& field[y+1][x] == 0) {
                        field[y+1][x] = field[y][x];
                        field[y][x] = 0;
                        y++;
                    }
                }
            }
        }
    }
    
    public static void printField(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
}
