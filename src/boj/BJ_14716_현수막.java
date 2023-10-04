package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14716_현수막 {
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] map = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    bfs(map, m, n, i, j);
                    ++answer;
                }
            }
        }

        System.out.println(answer);
    }

    private static void bfs(int[][] map, int m, int n, int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nr = cur[0] + dir[i][0];
                int nc = cur[1] + dir[i][1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n || map[nr][nc] != 1) continue;

                q.add(new int[]{nr, nc});
                map[nr][nc] = 2;
            }
        }
    }
}
