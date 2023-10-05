package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10159_저울 {
    private final static int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            dp[a][b] = 1;
        }

        // 플로이드 워셜
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    if (dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (dp[i][j] != INF || dp[j][i] != INF) ++cnt;
            }
            sb.append(N - cnt).append("\n");
        }
        System.out.print(sb);
    }
}
