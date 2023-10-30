package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ_2140_지뢰찾기 {

    private static int N;
    private static char[][] map;
    private static boolean[][] visited;
    private static int answer;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 지뢰가 존재할 수 없다면 종료한다. (테두리는 항상 열려있음)
        if (N < 3) {
            System.out.println("0");
            return;
        }

        visited = new boolean[N][N];
        // 최대의 지뢰를 구해야 하므로 안은 무조건 지뢰로 채운다.
        if (N > 4) answer = (N - 4) * (N - 4);

        solve();
        System.out.println(answer);
    }


    private static void solve() {
        Queue<int[]> q = new ArrayDeque<>();
        int chk = 0;

        while (chk < N * 4 - 4) {
            // 빈칸 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '#' || map[i][j] == ' ' || map[i][j] == '*' || visited[i][j]) continue;
                    int cnt = countAdjacentMatching(i, j, '*');
                    if (cnt == map[i][j] - '0') q.add(new int[]{i, j});
                }
            }

            int size = q.size();
            while (size-- > 0) {
                int[] poll = q.poll();
                visited[poll[0]][poll[1]] = true;
                ++chk;
                clearAdjacentWalls(poll[0], poll[1]);
            }

            // 지뢰 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '#' || map[i][j] == ' ' || map[i][j] == '*' || visited[i][j]) continue;
                    int cnt = countAdjacentMatching(i, j, '#') + countAdjacentMatching(i, j, '*');
                    if (cnt == map[i][j] - '0') q.add(new int[]{i, j});
                }
            }

            size = q.size();
            while (size-- > 0) {
                int[] poll = q.poll();
                visited[poll[0]][poll[1]] = true;
                ++chk;
                markAdjacentWalls(poll[0], poll[1]);
            }
        }
    }

    // 인접한 곳에서 flag의 개수 세기
    private static int countAdjacentMatching(int i, int j, char flag) {
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nr = i + dir[k][0];
            int nc = j + dir[k][1];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (map[nr][nc] == flag) ++cnt;
        }
        return cnt;
    }

    // 주변 벽 빈칸으로 바꾸기
    private static void clearAdjacentWalls(int i, int j) {
        for (int k = 0; k < 8; k++) {
            int nr = i + dir[k][0];
            int nc = j + dir[k][1];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != '#') continue;
            map[nr][nc] = ' ';
        }
    }

    // 주변 벽 지뢰로 바꾸기
    private static void markAdjacentWalls(int i, int j) {
        for (int k = 0; k < 8; k++) {
            int nr = i + dir[k][0];
            int nc = j + dir[k][1];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != '#') continue;
            map[nr][nc] = '*';
            ++answer;
        }
    }

}