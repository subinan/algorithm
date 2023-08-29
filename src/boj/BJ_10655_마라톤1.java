package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10655_마라톤1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] checkPoints = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            checkPoints[i][0] = Integer.parseInt(st.nextToken());
            checkPoints[i][1] = Integer.parseInt(st.nextToken());
        }

        // 모든 경로를 방문했을 때의 길이 구하기
        int dist = 0;
        for (int i = 1; i < N; i++) {
            dist += getDistance(checkPoints[i - 1], checkPoints[i]);
        }

        // 체크포인트를 하나씩 스킵하며 최소 거리 구하기
        int ans = dist;
        for (int i = 1; i < N - 1; i++) {
            int skipDist = dist
                    - getDistance(checkPoints[i - 1], checkPoints[i])
                    - getDistance(checkPoints[i], checkPoints[i + 1])
                    + getDistance(checkPoints[i - 1], checkPoints[i + 1]);
            ans = Math.min(ans, skipDist);
        }

        System.out.println(ans);
    }

    private static int getDistance(int[] c1, int[] c2) {
        return Math.abs(c1[0] - c2[0]) + Math.abs(c1[1] - c2[1]);
    }

}
