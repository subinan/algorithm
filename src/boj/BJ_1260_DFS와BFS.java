package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1260_DFS와BFS {
    private static int N, M, V;
    private static boolean[] visited;
    private static List<Integer>[] adjList;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 정점 번호 순으로 방문하기 위해 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);
        }

        sb = new StringBuilder();

        visited = new boolean[N + 1];
        dfs(V, 0);

        sb.append("\n");
        bfs(V);

        System.out.println(sb);
    }

    private static void dfs(int cur, int depth) {
        visited[cur] = true;
        sb.append(cur).append(" ");

        if (depth == N - 1) {
            return;
        }

        for (int n: adjList[cur]) {
            if (visited[n]) continue;
            dfs(n, depth + 1);
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque();
        visited = new boolean[N + 1];

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");

            for (int n: adjList[cur]) {
                if (visited[n]) continue;
                q.add(n);
                visited[n] = true;
            }
        }
    }


}
