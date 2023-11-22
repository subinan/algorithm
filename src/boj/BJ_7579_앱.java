package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_7579_앱 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        int[] cost = new int[n + 1];
        int totalCost = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        int[][] dp = new int[n + 1][totalCost + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= totalCost; j++) {
                if (j - cost[i] >= 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + memory[i]);
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        for (int i = 0; i <= totalCost; i++) {
            if (dp[n][i] >= m) {
                System.out.println(i);
                break;
            }
        }
    }

}
