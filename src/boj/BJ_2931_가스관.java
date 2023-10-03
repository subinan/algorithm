package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2931_가스관 {
    private static int flag, answer[];
    private static final int[][] dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][];
        int[] moscow = null, zagreb = null;

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'M') moscow = new int[]{i, j};
                if (map[i][j] == 'Z') zagreb = new int[]{i, j};
            }
        }

        answer = new int[2];
        bfs(map, R, C, moscow);
//        bfs(map, R, C, zagreb);

        System.out.println(answer[0] + " " + answer[1] + " " + getChar(flag));
    }

    private static void bfs(char[][] map, int r, int c, int[] start) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[r][c];
        visited[start[0]][start[1]] = true;

        for (int i = 0; i < 4; i++) {
            int nr = start[0] + dir[i][0];
            int nc = start[1] + dir[i][1];
            if (nr < 0 || nr >= r || nc < 0 || nc >= c || map[nr][nc] == '.'
                || map[nr][nc] == 'M' || map[nr][nc] == 'Z') continue;

            q.add(new int[]{nr, nc, i});
            visited[nr][nc] = true;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            char b = map[cur[0]][cur[1]];
            if (b == '.') {
                answer[0] = cur[0] + 1;
                answer[1] = cur[1] + 1;
                flag |= (1 << (cur[2] + 2) % 4);

                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dir[i][0];
                    int nc = cur[1] + dir[i][1];
                    if (nr < 0 || nr >= r || nc < 0 || nc >= c ||
                            map[nr][nc] == 'M' || map[nr][nc] == 'Z' ||
                            map[nr][nc] == '.' || visited[nr][nc]) continue;

                    q.add(new int[]{nr, nc, i});
                    visited[nr][nc] = true;
                }
                continue;
            }

            int[] dirs = getDir(b);
            for (int d: dirs) {
                int nr = cur[0] + dir[d][0];
                int nc = cur[1] + dir[d][1];
                if (nr < 0 || nr >= r || nc < 0 || nc >= c ||
                        map[nr][nc] == 'M' || map[nr][nc] == 'Z' ||
                        (map[nr][nc] != '.' && visited[nr][nc])) continue;

                q.add(new int[]{nr, nc, d});
                visited[nr][nc] = true;
            }
        }
    }

    private static int[] getDir(char c) {
        if (c == '|') return new int[]{0, 2};
        if (c == '-') return new int[]{1, 3};
        if (c == '+') return new int[]{0, 1, 2, 3};
        if (c == '1') return new int[]{0, 3};
        if (c == '2') return new int[]{2, 3};
        if (c == '3') return new int[]{1, 2};
        if (c == '4') return new int[]{0, 1};
        return null;
    }

    private static char getChar(int flag) {
        if ((flag & (1 << 0)) != 0 && (flag & (1 << 1)) != 0
            && (flag & (1 << 2)) != 0 && (flag & (1 << 3)) != 0) return '+';
        if ((flag & (1 << 0)) != 0 && (flag & (1 << 2)) != 0) return '|';
        if ((flag & (1 << 1)) != 0 && (flag & (1 << 3)) != 0) return '-';
        if ((flag & (1 << 0)) != 0 && (flag & (1 << 3)) != 0) return '1';
        if ((flag & (1 << 2)) != 0 && (flag & (1 << 3)) != 0) return '2';
        if ((flag & (1 << 1)) != 0 && (flag & (1 << 2)) != 0) return '3';
        return '4';
    }
}
