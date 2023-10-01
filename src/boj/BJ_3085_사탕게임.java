package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_3085_사탕게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, getCount(map, i, i));
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] != map[i][j - 1]) {
                    swap(map, i, j, i, j - 1);
                    answer = Math.max(answer, getCount(map, i, j));
                    answer = Math.max(answer, getCount(map, i, j - 1));
                    swap(map, i, j, i, j - 1);
                }
                if (map[i][j] != map[i - 1][j]) {
                    swap(map, i, j, i - 1, j);
                    answer = Math.max(answer, getCount(map, i, j));
                    answer = Math.max(answer, getCount(map, i - 1, j));
                    swap(map, i, j, i - 1, j);

                }
            }
        }

        System.out.println(answer);
    }

    private static void swap(char[][] map, int r1, int c1, int r2, int c2) {
        char tmp = map[r1][c1];
        map[r1][c1] = map[r2][c2];
        map[r2][c2] = tmp;
    }

    private static int getCount(char[][] map, int r, int c) {
        int rCnt = 1, cCnt = 1;
        int max = 1;

        for (int i = 1; i < map.length; i++) {
            // 행 확인
            if (map[r][i - 1] == map[r][i]) {
                ++rCnt;
            } else {
                max = Math.max(max, rCnt);
                rCnt = 1;
            }

            // 열 확인
            if (map[i - 1][c] == map[i][c]) {
                ++cCnt;
            } else {
                max = Math.max(max, cCnt);
                cCnt = 1;
            }
        }
        return Math.max(max, Math.max(rCnt, cCnt));
    }

}
