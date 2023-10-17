package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16929_TwoDots {
    private static int N, M;
    private static char[][] map;
    private static boolean[][] visited;
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

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (dfs(i, j, i, j, 1)) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }

        System.out.println("No");
    }

    private static boolean dfs(int sr, int sc, int r, int c, int cnt) {
        char m = map[r][c];

        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != m) continue;

            if (nr == sr && nc == sc && cnt > 2) return true;

            if (!visited[nr][nc]) {
                if (dfs(sr, sc, nr, nc, cnt + 1)) return true;
                visited[nr][nc] = false;
            }
        }

        return false;
    }

}
