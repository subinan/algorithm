package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_2643_색종이올려놓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            if (arr[i][0] < arr[i][1]) {
                int tmp = arr[i][0];
                arr[i][0] = arr[i][1];
                arr[i][1] = tmp;
            }
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> (a[0] + a[1])));

        int[] dp = new int[N];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j][0] <= arr[i][0] && arr[j][1] <= arr[i][1] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    answer = Math.max(answer, dp[i]);
                }
            }
        }

        System.out.println(answer);
    }
}
