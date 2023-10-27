package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2302_극장좌석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        //dp[i]: 좌석이 i개일 때, 배치 가능한 경우의 수
        // dp[i] = dp[i - 1] + dp[i - 2]
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int start = 0;
        long answer = 1;

        for (int i = 0; i < M; i++) {
            int end = Integer.parseInt(br.readLine());
            answer *= dp[end - start - 1];
            start = end;
        }
        answer *= dp[N - start];

        System.out.println(answer);
    }
}