package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2159_케익배달 {
    private static final int[][] dir = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] coord = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            coord[i][0] = Integer.parseInt(st.nextToken());
            coord[i][1] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N + 1][5];
        for (int i = 0; i < 5; i++) {
            dp[1][i] = getDistance(coord[0][0], coord[0][1],
                    coord[1][0] + dir[i][0], coord[1][1] + dir[i][1]);
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 5; j++) {
                dp[i][j] = (long) 1e18;
                for (int k = 0; k < 5; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i - 1][k] + getDistance(coord[i - 1][0] + dir[k][0], coord[i - 1][1] + dir[k][1],
                                    coord[i][0] + dir[j][0], coord[i][1] + dir[j][1]));
                }
            }
        }

        long answer = (long) 1e18;
        for (int i = 0; i < 5; i++) answer = Math.min(answer, dp[N][i]);
        System.out.println(answer);
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
