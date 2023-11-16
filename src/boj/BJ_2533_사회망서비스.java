package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/board/view/1366
public class BJ_2533_사회망서비스 {

    private static List<List<Integer>> adjList;
    private static int[][] dp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        dp = new int[N][2];
        visited = new boolean[N];

        solve(0);
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }

    private static void solve(int node) {
        List<Integer> nodes = adjList.get(node);
        visited[node] = true;
        dp[node][0] = 1; // 현재 노드가 얼리 아답터

        for (int child : nodes) {
            if (visited[child]) continue;
            solve(child);
            dp[node][0] += Math.min(dp[child][0], dp[child][1]); // 얼리 아답터든 아니든 상관 x
            dp[node][1] += dp[child][0]; // 자식이 무조건 얼리 아답터야 함
        }
    }

}