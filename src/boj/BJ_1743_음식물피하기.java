package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1743_음식물피하기 {
    private static boolean[][] visited;
    private static char[][] map;
    private static int N, M, K;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = '#';
        }

        visited = new boolean[N][M];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == '#') {
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            ++cnt;

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dir[i][0];
                int nc = cur[1] + dir[i][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M ||
                    visited[nr][nc] || map[nr][nc] != '#') continue;

                q.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        return cnt;
    }

}
