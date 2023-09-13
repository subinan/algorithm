package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_2015_수들의합4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[N + 1];
        int[] dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp[i - 1] + A[i];
        }

        long ans = 0;
        Map<Integer, Long> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            // 1 ≤ i ≤ j ≤ N인 정수 i와 j에 대해 A[i]부터 A[j]까지의 합이 K인 개수 더하기
            if (dp[i] == K) ++ans;
            if (map.containsKey(dp[i] - K)) ans += map.get(dp[i] - K);

            // 누적합이 dp[i]인 경우의 수 기록
            if (!map.containsKey(dp[i])) map.put(dp[i], 1L);
            else map.put(dp[i], map.get(dp[i]) + 1);
        }

        System.out.println(ans);
    }
}
