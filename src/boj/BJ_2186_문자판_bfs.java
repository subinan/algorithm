package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2186_문자판_bfs {
    private static char word[], map[][];
    private static int N, M, K, dp[][][];
    private static Queue<int[]> q;
    private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        word = br.readLine().toCharArray();

        q = new ArrayDeque<>();
        dp = new int[N][M][word.length + 1];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == word[0]) {
                    q.add(new int[]{r, c});
                    dp[r][c][0] = 1;
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int idx = 1;

        while (!q.isEmpty() && idx < word.length) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    for (int k = 1; k <= K; k++) {
                        int nr = cur[0] + dir[i][0] * k;
                        int nc = cur[1] + dir[i][1] * k;

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M ||
                                map[nr][nc] != word[idx]) continue;

                        if (dp[nr][nc][idx] == 0) q.add(new int[]{nr, nc});
                        dp[nr][nc][idx] += dp[cur[0]][cur[1]][idx - 1];
                    }
                }
            }
            ++idx;
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt += dp[i][j][word.length - 1];
            }
        }
        return cnt;
    }
}
