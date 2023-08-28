package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_18427_함께블록쌓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        List<Integer>[] blocks = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            blocks[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                blocks[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int[][] dp = new int[N + 1][H + 1];
        for (int i = 0; i <= N; i++) dp[i][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= H; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int b : blocks[i]) {
                    if (j >= b) {
                        dp[i][j] += dp[i - 1][j - b];
                        dp[i][j] %= 10007;
                    }
                }
            }
        }

        System.out.println(dp[N][H]);
    }
}
