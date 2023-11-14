package boj;

import java.util.Scanner;

// https://restudycafe.tistory.com/487 -> 포함과 배제의 원리
public class BJ_16565_N포커 {

    private static final int MOD = 10007;
    private static int[][] comb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        initComb();

        int n = sc.nextInt();
        int ans = 0;
        for (int i = 4; i <= n; i += 4) {
            if ((i / 4) % 2 == 1) {
                ans += comb[13][i / 4] * comb[52 - i][n - i] % MOD;
            } else {
                ans += MOD;
                ans -= comb[13][i / 4] * comb[52 - i][n - i] % MOD;
            }
            ans %= MOD;
        }
        System.out.println(ans);

        sc.close();
    }

    private static void initComb() {
        comb = new int[53][53];
        for (int i = 0; i <= 52; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j] % MOD;
            }
        }
    }

}
