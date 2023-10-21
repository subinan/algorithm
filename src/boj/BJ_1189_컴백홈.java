package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1189_컴백홈 {

    private static int R, C, K, cnt;
    private static char[][] map;
    private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(0, new int[]{R - 1, 0});
        System.out.println(cnt);
    }

    private static void dfs(int depth, int[] now) {
        if (depth == K - 1) {
            if (now[0] == 0 && now[1] == C - 1) ++cnt;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = now[0] + dir[d][0];
            int nc = now[1] + dir[d][1];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'T') continue;

            map[now[0]][now[1]] = 'T'; // 방문체크
            dfs(depth + 1, new int[] {nr, nc});
            map[now[0]][now[1]] = '.'; // 방문취
        }
    }
}
