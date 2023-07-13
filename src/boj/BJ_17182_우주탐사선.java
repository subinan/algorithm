package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17182_우주탐사선 {

    private static int N, K, ans;
    private static int[][] T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                T[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j || j == k || k == i) continue;
                    T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j]);
                }
            }
        }

        ans = Integer.MAX_VALUE;
        dfs(K, 1, 1 << K, 0);

        System.out.println(ans);
    }

    private static void dfs(int idx, int cnt, int bit, int sum) {
        if (cnt == N) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) > 0) continue;

            dfs(i, cnt + 1, bit | (1 << i), sum + T[idx][i]);
        }
    }

}


