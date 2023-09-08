package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2186_문자판_dfs {
    private static char word[], map[][];
    private static int N, M, K, dp[][][];
    private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        word = br.readLine().toCharArray();
        dp = new int[N][M][word.length + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int ans = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == word[0]) {
                    ans += dfs(r, c, 0);
                }
            }
        }

        System.out.println(ans);
    }

    private static int dfs(int r, int c, int idx) {
        if (dp[r][c][idx] != -1) return dp[r][c][idx];
        if (idx == word.length - 1) return dp[r][c][idx] = 1;

        dp[r][c][idx] = 0;

        for (int i = 0; i < 4; i++) {
            for (int k = 1; k <= K; k++) {
                int nr = r + dir[i][0] * k;
                int nc = c + dir[i][1] * k;

                if (nr < 0 || nr >= N || nc < 0 || nc >= M ||
                        map[nr][nc] != word[idx + 1]) continue;

                dp[r][c][idx] += dfs(nr, nc, idx + 1);
            }
        }

        return dp[r][c][idx];
    }
}
