package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_25979_시간구간다중업데이트최대합 {
    private final static int MAX = 86400;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] time = new int[MAX];
        long[] dp = new long[MAX];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int start = timeToSecond(st.nextToken());
                int end = timeToSecond(st.nextToken());

                time[start] += 1;
                time[end] -= 1;
            } else if (type == 2) {
                // time 배열 값 정리
                for (int i = 1; i < MAX; i++) {
                    time[i] = time[i - 1] + time[i];
                }

                // 구간합 구하기
                dp[0] = time[0];
                for (int i = 1; i < MAX; i++) dp[i] = dp[i - 1] + time[i];

                // 부분합 최댓값 구하기
                int len = timeToSecond(st.nextToken());
                long max = dp[len - 1];
                for (int i = len; i < MAX; i++) max = Math.max(max, dp[i] - dp[i - len]);
                System.out.println(max);
                break;
            }
        }

    }

    private static int timeToSecond(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        return  hour * 60 * 60 + minute * 60 + second;
    }
}

/*

4
1 23:59:00 23:59:00
1 23:59:00 23:59:59
1 23:59:00 23:59:59
2 00:01:00

4
1 00:59:00 00:59:00
1 00:59:00 00:59:59
1 00:59:00 00:59:59
2 00:01:00

4
1 00:59:00 00:59:00
1 00:59:00 01:00:00
1 00:59:00 01:00:00
2 00:01:00

4
1 00:00:00 00:00:00
1 00:00:00 00:01:00
1 00:00:00 00:01:00
2 00:01:00

4
1 00:00:00 00:00:00
1 00:01:00 00:02:00
1 00:01:00 00:02:00
2 00:01:00

*/