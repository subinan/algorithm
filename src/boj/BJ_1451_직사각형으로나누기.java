package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1451_직사각형으로나누기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 입력
        int[][] num = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                num[i][j] = input[j - 1] - '0';
            }
        }

        // 2차원 누적합
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + num[i][j];
            }
        }

        // 3개의 직사각형으로 나눠서 최댓값 구하기
        // 총 6가지 모양으로 나눌 수 있다 -> 하나하나 직접 코드 짜야함 ㅜ
        long answer = 0;

        // =
        for (int i = 1; i <= N - 2; i++) {
            for (int j = i + 1; j <= N - 1; j++) {
                answer = Math.max(answer,
                        getRectangleSum(dp, 1, 1, i, M)
                                * getRectangleSum(dp, i + 1, 1, j, M)
                                * getRectangleSum(dp, j + 1, 1, N, M));
            }
        }

        // ||
        for (int i = 1; i <= M - 2; i++) {
            for (int j = i + 1; j <= M - 1; j++) {
                answer = Math.max(answer,
                        getRectangleSum(dp, 1, 1, N, i)
                                * getRectangleSum(dp, 1, i + 1, N, j)
                                * getRectangleSum(dp, 1, j + 1, N, M));
            }
        }

        // ㅏ, ㅓ, ㅗ, ㅜ
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= M - 1; j++) {
                answer = Math.max(answer,
                        getRectangleSum(dp, 1, 1, N, j)
                                * getRectangleSum(dp, 1, j + 1, i, M)
                                * getRectangleSum(dp, i + 1, j + 1, N, M)); // ㅏ

                answer = Math.max(answer,
                        getRectangleSum(dp, 1, 1, i, j)
                                * getRectangleSum(dp, i + 1, 1, N, j)
                                * getRectangleSum(dp, 1, j + 1, N, M)); // ㅓ

                answer = Math.max(answer,
                        getRectangleSum(dp, 1, 1, i, j)
                                * getRectangleSum(dp, 1, j + 1, i, M)
                                * getRectangleSum(dp, i + 1, 1, N, M)); // ㅗ

                answer = Math.max(answer,
                        getRectangleSum(dp, 1, 1, i, M)
                                * getRectangleSum(dp, i + 1, 1, N, j)
                                * getRectangleSum(dp, i + 1, j + 1, N, M)); // ㅜ
            }
        }

        System.out.println(answer);
    }

    private static long getRectangleSum(int[][] dp, int sr, int sc, int er, int ec) {
        return dp[er][ec] - dp[er][sc - 1] - dp[sr - 1][ec] + dp[sr - 1][sc - 1];
    }

}
