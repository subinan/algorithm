package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_14497_주난의난 {
    private static int N, M;
    private static char[][] map;
    private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;

        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs(x1, y1, x2, y2));
    }

    private static int bfs(int x1, int y1, int x2, int y2) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(i -> i[2]));
        q.add(new int[]{x1, y1, 0});

        boolean[][] visited = new boolean[N][M];
        visited[x1][y1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dir[i][0];
                int nc = cur[1] + dir[i][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;

                if (nr == x2 && nc == y2) return cur[2] + 1;

                if (map[nr][nc] == '1') {
                    map[nr][nc] = '0';
                    q.add(new int[]{nr, nc, cur[2] + 1});
                } else {
                    q.add(new int[]{nr, nc, cur[2]});
                }

                visited[nr][nc] = true;
            }
        }

        return -1;
    }

}
