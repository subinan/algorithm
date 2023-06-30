package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_9461_파도반수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            long[] dp = new long[N > 2 ? N + 1 : 3];
            dp[1] = 1;
            dp[2] = 1;

            for (int i = 3; i <= N; i++) {
                dp[i] = dp[i - 2] + dp[i - 3];
            }

            sb.append(dp[N]).append("\n");
        }

        System.out.print(sb);
    }

}
