package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_2001_파리퇴치 {

    static int T, N, M, ans;
    static int[][] F, DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            F = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    F[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 누적 파리 수를 구한다.
            DP = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    DP[i][j] = DP[i - 1][j] + DP[i][j - 1] - DP[i - 1][j - 1] + F[i][j];
                }
            }

            ans = 0;
            for (int i = 0; i <= N - M; i++) {
                for (int j = 0; j <= N - M; j++) {
                    // (3,3)부터 (5,5)까지의 합은
                    // (1,1)~(5,5)에서 
                    // (1,1)~(2,5)와 (1,1)~(5,2)를 빼고 
                    // (1,1)~(2,2)를 더한 것과 같다.
                    int cnt = DP[i + M][j + M] - DP[i][j + M] - DP[i + M][j] + DP[i][j];
                    ans = Math.max(ans, cnt);
                }
            }

            // 출력
            sb.append("#" + t + " " + ans + "\n");
        }

        System.out.print(sb);
    }
}