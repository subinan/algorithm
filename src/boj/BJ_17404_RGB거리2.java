package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17404_RGB거리2 {

    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] rgb = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rgb[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = (int) 1e9;

        int[][] dp = new int[N][3];
        // R, G, B 순서로 첫번째 집 칠하고 비용 구하기
        for (int k = 0; k < 3; k++) {
            // 첫번째 집 칠하기
            for (int i = 0; i < 3; i++) {
                if (i == k) dp[0][i] = rgb[0][i];
                else dp[0][i] = INF;
            }

            // 두번째 집부터 N번 집까지 칠하기
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + rgb[i][j];
                }
            }

            // 정답 구하기 (첫번째 집과 마지막 집의 색은 달라야 한다)
            for (int i = 0; i < 3; i++) {
                if (i != k) answer = Math.min(answer, dp[N - 1][i]);
            }
        }

        System.out.println(answer);
    }

}
