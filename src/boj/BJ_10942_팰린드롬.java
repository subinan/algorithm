package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10942_팰린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];
        for (int i = N - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < N; j++) {
                if ((j == i + 1 || dp[i + 1][j - 1] == 1)
                        && arr[i] == arr[j]) dp[i][j] = 1;
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            sb.append(dp[s][e]).append("\n");
        }
        System.out.print(sb);
    }
}
