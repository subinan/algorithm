package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16929_TwoDots_bfs {
    private static int N, M;
    private static char[][] map;
    private static int[][] visited;
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

        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    if (bfs(i, j)) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }

        System.out.println("No");
    }

    private static boolean bfs(int r, int c) {
        char m = map[r][c];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visited[r][c] = 1;

        int cnt = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            ++cnt;

            while (size-- > 0) {
                int[] cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dir[i][0];
                    int nc = cur[1] + dir[i][1];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != m) continue;

                    // bfs 탐색 시 동일한 시간에 도착하는 하나의 점이 존재한다면, 사이클이 존재하는 것이다.
                    if (visited[nr][nc] == cnt) return true;

                    if (visited[nr][nc] == 0) {
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = cnt;
                    }
                }
            }
        }

        return false;
    }

}
