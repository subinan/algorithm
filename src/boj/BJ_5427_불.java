package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5427_불 {
    private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            char[][] map = new char[h][];

            Queue<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[h][w];

            int[] start = null;
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '@') { // 시작점 기록
                        map[i][j] = '.';
                        start = new int[]{i, j, '@'};
                    } else if (map[i][j] == '*') { // 큐에 불 추가
                        q.add(new int[]{i, j, '*'});
                    }
                }
            }
            q.add(start);
            visited[start[0]][start[1]] = true; // 상근이가 지나간 경로를 방문 체크한다.

            int time = bfs(map, h, w, q, visited); // bfs

            if (time == -1) sb.append("IMPOSSIBLE\n");
            else sb.append(time).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(char[][] map, int h, int w, Queue<int[]> q, boolean[][] visited) {
        int time = 0; // 탈출하는데 걸리는 시간

        while (!q.isEmpty()) {
            int size = q.size();
            ++time;

            while (size-- > 0) {
                int[] cur = q.poll();

                // 탈출 여부 확인
                if (cur[2] == '@'
                        && (cur[0] == 0 || cur[0] == h - 1
                        || cur[1] == 0 || cur[1] == w - 1)) return time;

                // 상하좌우 방문
                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dir[i][0];
                    int nc = cur[1] + dir[i][1];

                    if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc] != '.') continue; // 빈칸으로만 이동

                    if (cur[2] == '*') map[nr][nc] = '*'; // 불 확산
                    else {
                        if (visited[nr][nc]) continue; // 방문 체크
                        visited[nr][nc] = true;
                    }
                    q.add(new int[]{nr, nc, cur[2]}); // 큐에 넣기
                }
            }
        }

        return -1; // 탈출 불가
    }
}
