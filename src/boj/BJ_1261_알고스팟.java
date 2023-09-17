package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1261_알고스팟 {

    private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs(N, M, map));
    }

    private static int bfs(int n, int m, char[][] map) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(i -> i[2]));
        q.add(new int[]{0, 0, 0});

        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == n - 1 && cur[1] == m - 1) return cur[2];

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dir[i][0];
                int nc = cur[1] + dir[i][1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;

                visited[nr][nc] = true;

                if (map[nr][nc] == '1') q.add(new int[]{nr, nc, cur[2] + 1});
                else q.add(new int[]{nr, nc, cur[2]});
            }
        }

        return -1;
    }
}
