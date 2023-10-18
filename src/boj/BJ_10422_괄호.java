package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_10422_괄호 {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // init
        long[] dp = new long[5001];
        dp[0] = 1;
        dp[2] = 1;

        for (int i = 2; i <= 2500; i++) {
            for (int j = 0; j < i; j++) {
                dp[i * 2] += dp[j * 2] * dp[(i - j - 1) * 2];
                dp[i * 2] %= MOD;
            }
        }

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int L = Integer.parseInt(br.readLine());
            sb.append(dp[L]).append("\n");
        }
        System.out.print(sb);
    }

}
