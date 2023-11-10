package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://js1jj2sk3.tistory.com/3
public class BJ_11066_파일합치기 {

    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] sum = new int[K + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                sum[i] = Integer.parseInt(st.nextToken()) + sum[i - 1];
            }

            int[][] dp = new int[K + 1][K + 1];
            for (int k = 1; k < K; k++) { // 파일의 범위 k -> k가 2이면 1~3, 2~4, 3~5 파일을 합침
                for (int i = 1; i + k <= K ; i++) { // 합칠 범위의 첫번째 파일
                    int j = i + k; // 합칠 범위의 마지막 파일 -> k개의 파일을 합치므로 i(시작) + k
                    dp[i][j] = INF; // i~j 파일을 합칠 때의 최소 비용
                    for (int mid = i; mid < j; mid++) { // 범위를 나누는 기준점
                        dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j] + sum[j] - sum[i - 1]); // 범위를 나눠서 최솟값 갱신
                    }
                }
            }

            System.out.println(dp[1][K]);
        }
    }

}
