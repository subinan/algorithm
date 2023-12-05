package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17136_색종이붙이기 {

    private static int[][] paper;
    private static int[] left;
    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        paper = new int[10][10];
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        left = new int[5];
        Arrays.fill(left, 5);

        dfs(0, 0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void dfs(int r, int c, int cnt) {
        if (c == 10) {
            ++r;
            c = 0;
        }

        if (r == 10) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (paper[r][c] == 0) {
            dfs(r, c + 1, cnt);
            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (left[i - 1] > 0 && chk(r, c, i)) {
                fill(r, c, i, 0);
                --left[i - 1];
                
                dfs(r, c + 1, cnt + 1);
                
                fill(r, c, i,1);
                ++left[i - 1];
            }
        }
    }

    private static void fill(int r, int c, int len, int p) {
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                paper[i][j] = p;
            }
        }
    }

    private static boolean chk(int r, int c, int len) {
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (i >= 10 || j >= 10 || paper[i][j] == 0) return false;
            }
        }
        return true;
    }

}
