package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2234_성곽 {

    private static int N, M;
    private static boolean[][][] map;
    private static int[] answer;
    private static final int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int bit = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 4; k++) {
                    if ((bit & (1 << k)) == 0) map[i][j][k] = true;
                }
            }
        }

        answer = new int[3];

        // 방의 개수와 크기 구하기
        List<Integer> roomSizes = new ArrayList<>();
        roomSizes.add(0);

        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    int size = bfs(i, j, ++answer[0], visited);
                    answer[1] = Math.max(answer[1], size);
                    roomSizes.add(size);
                }
            }
        }

        // 벽 뚫었을 때 최대 방 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[] d : dir) {
                    int nr = i + d[0];
                    int nc = j + d[1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[i][j] == visited[nr][nc]) continue;

                    answer[2] = Math.max(answer[2], roomSizes.get(visited[i][j]) + roomSizes.get(visited[nr][nc]));
                }
            }
        }

        System.out.println(answer[0]);
        System.out.println(answer[1]);
        System.out.println(answer[2]);
    }

    private static int bfs(int r, int c, int idx, int[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{r, c});
        visited[r][c] = idx;

        int size = 1;
        while (!q.isEmpty()) {
            int[] poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = poll[0] + dir[i][0];
                int nc = poll[1] + dir[i][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M ||
                    visited[nr][nc] != 0 || !map[poll[0]][poll[1]][i]) continue;

                q.add(new int[]{nr, nc});
                visited[nr][nc] = idx;
                ++size;
            }
        }
        return size;
    }

}
