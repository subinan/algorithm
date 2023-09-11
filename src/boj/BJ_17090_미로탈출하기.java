package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17090_미로탈출하기 {
    private static int N, M;
    private static char[][] map;
    private static boolean[][] visited;

    private static final String DIR = "URDL";
    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(i, j)) ++answer;
            }
        }

        System.out.println(answer);
    }

    private static boolean dfs(int r, int c) {
        if (map[r][c] == 'T') return true;
        else if (map[r][c] == 'F') return false;

        int d = DIR.indexOf(map[r][c]);
        int nr = r + dir[d][0];
        int nc = c + dir[d][1];

        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
            map[r][c] = 'T';
        } else if (visited[nr][nc]) {
            map[r][c] = 'F';
        } else {
            visited[r][c] = true;
            map[r][c] = dfs(nr, nc) ? 'T' : 'F';
            visited[r][c] = false;
        }

        return map[r][c] == 'T';
    }
}
