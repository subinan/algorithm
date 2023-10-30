package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ_2140_지뢰찾기 {

    private static int N;
    private static char[][] map;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private static class Info {
        int r;
        int c;
        char flag;

        public Info(int r, int c, char flag) {
            this.r = r;
            this.c = c;
            this.flag = flag;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        if (N < 3) System.out.println(0); // N < 3이면 지뢰가 존재할 수 없다면 종료한다. (테두리는 항상 열려있음)
        else System.out.println(solve());
    }

    private static int solve() {
        Queue<Info> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        int chk = N * 4 - 4;
        int answer = 0;

        if (N > 4) answer = (N - 4) * (N - 4); // 지뢰는 최대로 -> 안쪽은 지뢰로 채운다.

        while (chk > 0) {
            // 빈칸 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '#' || map[i][j] == ' ' || map[i][j] == '*' || visited[i][j]) continue;
                    int cnt = countAdjacentMatching(i, j, '*');
                    if (cnt == map[i][j] - '0') q.add(new Info(i, j, ' '));
                }
            }

            while (!q.isEmpty()) {
                Info info = q.poll();
                visited[info.r][info.c] = true;
                --chk;
                changeAdjacentWalls(info);
            }

            // 지뢰 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '#' || map[i][j] == ' ' || map[i][j] == '*' || visited[i][j]) continue;
                    int cnt = countAdjacentMatching(i, j, '#') + countAdjacentMatching(i, j, '*');
                    if (cnt == map[i][j] - '0') q.add(new Info(i, j, '*'));
                }
            }

            while (!q.isEmpty()) {
                Info info = q.poll();
                visited[info.r][info.c] = true;
                --chk;
                answer += changeAdjacentWalls(info);
            }
        }

        return answer;
    }

    private static int countAdjacentMatching(int r, int c, char flag) {
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nr = r + dir[k][0];
            int nc = c + dir[k][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if (map[nr][nc] == flag) ++cnt;
        }
        return cnt;
    }


    private static int changeAdjacentWalls(Info info) {
        int changeCnt = 0;
        for (int k = 0; k < 8; k++) {
            int nr = info.r + dir[k][0];
            int nc = info.c + dir[k][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != '#') continue;

            map[nr][nc] = info.flag;
            ++changeCnt;
        }
        return changeCnt;
    }

}