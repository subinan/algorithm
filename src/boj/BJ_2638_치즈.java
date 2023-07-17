package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2638_치즈 {

    private static int N, M, cnt;
    private static int[][] map;
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) ++cnt;
            }
        }

        int ans = 0;
        while (cnt > 0) {
            meltingCheese();
            ++ans;
        }

        System.out.println(ans);
    }

    private static void meltingCheese() {
        boolean[][] visited = new boolean[N][M];

        // 외부 공기와 접촉 여부를 확인하기 위해 네 모서리를 돌며 확인한다.
        for (int i = 0; i < N; i++) {
            if (i == 0 || i == N - 1) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && map[i][j] == 0) bfs(i, j, visited);
                }
            } else {
                if (!visited[i][0] && map[i][0] == 0) bfs(i, 0, visited);
                if (!visited[i][M - 1] && map[i][M - 1] == 0) bfs(i, M - 1, visited);
            }
        }

        // 외부 공기와 2번 이상 접촉한 치즈를 녹인다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0;
                    --cnt;
                }
            }
        }

//        print();
    }

    private static void bfs(int r, int c, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dir[i][0];
                int nc = cur[1] + dir[i][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                // 방문한 적이 있는 치즈라면 외부 공기와 2번 이상 접촉한 것이다.
                if (map[nr][nc] == 1 && visited[nr][nc]) {
                    map[nr][nc] = 2;
                    continue;
                }
                // 방문 체크
                if (visited[nr][nc]) continue;

                // 외부 공기가 닿는 곳만 확인한다.
                if (map[nr][nc] == 0) q.add(new int[]{nr, nc});
                // 치즈는 큐에 넣지 않지만 외부 공기와의 접촉 여부를 확인하기 위해 방문 체크를 한다.
                visited[nr][nc] = true;
            }
        }
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
