package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2296_건물짓기 {

    private static class Building implements Comparable<Building> {
        int x;
        int y;
        int c;

        public Building(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }


        @Override
        public int compareTo(Building o) {
            int comp = x - o.x;
            if (comp == 0) return y - o.y;
            return comp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Building[] buildings = new Building[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            buildings[i] = new Building(x, y, c);
        }

        Arrays.sort(buildings);

        // dp[i][0]: i번째 건물까지 1, 3분면에 건물 건설
        // dp[i][1]: i번째 건물까지 2, 4분면에 건물 건설
        int[][] dp = new int[N][2];
        for (int i = 0; i < N; i++) {
            dp[i][0] = buildings[i].c;
            dp[i][1] = buildings[i].c;
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (buildings[i].y > buildings[j].y) dp[i][0] = Math.max(dp[i][0], dp[j][0] + buildings[i].c);
                if (buildings[i].y < buildings[j].y) dp[i][1] = Math.max(dp[i][1], dp[j][1] + buildings[i].c);
            }
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(max);
    }

}
