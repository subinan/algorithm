package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16964_DFS스페셜저지 {

    private static int N, idx, order[];
    private static boolean answer, visited[];
    private static List<List<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();

        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        order = new int[N];
        int[] orderIdx = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            order[i] = Integer.parseInt(st.nextToken());
            orderIdx[order[i]] = i;
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(adj.get(i), Comparator.comparingInt(n -> orderIdx[n]));
        }

        answer = true;
        visited = new boolean[N + 1];
        if (order[0] != 1) answer = false;
        else dfs(1);

        if (answer) System.out.println("1");
        else System.out.println("0");
    }

    private static void dfs(int node) {
        visited[node] = true;

        if (order[idx++] != node) {
            answer = false;
            return;
        }

        for (int next : adj.get(node)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

}
