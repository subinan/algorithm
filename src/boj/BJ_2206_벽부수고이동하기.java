package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206_벽부수고이동하기 {

    private static char[][] map;
    private static int N, M;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs(0, 0));
    }

    private static int bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];

        q.add(new int[]{r, c, 0, 1});
        visited[r][c][0] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            if (poll[0] == N - 1 && poll[1] == M - 1) return poll[3];

            for (int[] d : dir) {
                int nr = poll[0] + d[0];
                int nc = poll[1] + d[1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                int brk = poll[2];
                if (map[nr][nc] == '1') {
                    if (brk == 1) continue;
                    else ++brk;
                }

                if (!visited[nr][nc][brk]) {
                    q.add(new int[]{nr, nc, brk, poll[3] + 1});
                    visited[nr][nc][brk] = true;
                }
            }
        }

        return -1;
    }

}
