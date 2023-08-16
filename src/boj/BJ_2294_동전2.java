package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2294_동전2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        int[] dp = new int[K + 1];
        Arrays.fill(dp, (int) 1e9);

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
            if (coins[i] <= K) dp[coins[i]] = 1;
        }
        Arrays.sort(coins);

        for (int i = 1; i <= K; i++) {
            for (int coin: coins) {
                if (i - coin < 0) break;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        System.out.println(dp[K] == (int)1e9 ? -1 : dp[K]);
    }
}
