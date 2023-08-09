package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_15989_123더하기4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[][] dp = new int[10001][4];
        // 수식은 무조건 오름차순이어야 한다. (중복 제거를 위해)
        dp[1][1] = 1; // 1
        dp[2][1] = 1; dp[2][2] = 1; // 1+1, 2
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1; // 1+1+1, 1+2, 3
        for (int i = 4; i <= 10000; i++) {
            dp[i][1] = dp[i - 1][1]; // +1을 해서 만들 수 있는 수 (ex. 1+1+1+1) (1+2+1, 3+1은 내림차순이라 불가능!!)
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2]; // +2를 해서 만들 수 있는 수 (ex. 1+1+2, 2+2)
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3]; // +3을 해서 만들 수 있는 수 (ex. 1+3)
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
        }
        System.out.print(sb);
    }
}
