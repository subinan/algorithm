package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1520_내리막길 {

    private static int m, n, map[][], dp[][];
    private static boolean[][] visited;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[m][n];
        visited = new boolean[m][n];

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int r, int c) {
        if (r == m - 1 && c == n - 1) return 1;
        if (visited[r][c]) return dp[r][c];

        visited[r][c] = true;

        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nr >= m || nc < 0 || nc >= n
                    || map[r][c] <= map[nr][nc]) continue;

            dp[r][c] += dfs(nr, nc);
        }

        return dp[r][c];
    }

}
