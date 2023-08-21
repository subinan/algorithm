package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16918_봄버맨 {
    private static int R, C, N;
    private static Queue<int[]> q;
    private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        if (N % 2 == 0) {
            fillBomb(map);
            print(map);
            return;
        }

        q = new ArrayDeque<>();
        fillQueue(map);

        int cnt = 0;
        while (!q.isEmpty()) {
            cnt += 2;
            if (cnt >= N) break;
            int size = q.size();
            fillBomb(map);
            while (size-- > 0) {
                int[] cur = q.poll();
                map[cur[0]][cur[1]] = '.';
                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dir[i][0];
                    int nc = cur[1] + dir[i][1];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    map[nr][nc] = '.';
                }
            }
            fillQueue(map);
        }
        print(map);
    }

    private static void print(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) sb.append(String.valueOf(map[i])).append("\n");
        System.out.print(sb);
    }

    private static void fillBomb(char[][] map) {
        for (int i = 0; i < R; i++) Arrays.fill(map[i], 'O');
    }

    private static void fillQueue(char[][] map) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'O') q.add(new int[]{i, j});
            }
        }
    }
}
