package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2617_구슬찾기 {

    private static boolean[] visited;
    private static int[][] dp;
    private static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int half = (N + 1) / 2;

        list = new List[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(st.nextToken()) - 1;
            int light = Integer.parseInt(st.nextToken()) - 1;
            list[heavy].add(light);
        }

        dp = new int[N][2];

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, i);
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i][0] >= half || dp[i][1] >= half) ++answer;
        }
        System.out.println(answer);
    }

    public static void dfs(int current, int start) {

        visited[current] = true;

        for (int next : list[current]) {
            if (!visited[next]) {
                ++dp[start][0]; // 가벼운 구슬 증가
                ++dp[next][1]; // 무거운 구슬 증가
                dfs(next, start);
            }
        }
    }

}
