package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14889_스타트와링크 {

    private static int N, ans = Integer.MAX_VALUE;
    private static int[][] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0, 0);
        System.out.println(ans);
    }

    private static void comb(int cnt, int start, int select) {
        if (cnt == N / 2) {
            ans = Math.min(ans, statDiff(select));
            return;
        }

        for (int i = start; i < N; i++) {
            comb(cnt + 1, i + 1, select | (1 << i));
        }
    }

    private static int statDiff(int select) {
        int stat = 0;

        for (int i = 0; i < N; i++) {
            if ((select & (1 << i)) != 0) continue;
            for (int j = 0; j < N; j++) {
                if ((select & (1 << j)) != 0) continue;
                stat += S[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            if ((select & (1 << i)) == 0) continue;
            for (int j = 0; j < N; j++) {
                if ((select & (1 << j)) == 0) continue;
                stat -= S[i][j];
            }
        }

        return Math.abs(stat);
    }


}
