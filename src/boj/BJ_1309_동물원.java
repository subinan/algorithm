package boj;

import java.util.Scanner;

public class BJ_1309_동물원 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 3;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 9901;
        }

        System.out.println(dp[n]);

        sc.close();
    }

}

/*

dp[1] = 3
-> xx ox xo
dp[2] = 3 + 4
사자를 놓지 않는 경우 3, 사자를 놓는 경우 4
-> xx ox xo xx xx ox xo
   xx xx xx ox xo xo ox
dp[3] = 7 + 10
      = 7 + (3 * 2) + 4

dp[i] = dp[i - 1] + dp[i - 2] * 2 + dp[i - 1] - dp[i - 2]
      = 2 * 원dp[i - 1] + dp[i - 2]

 */