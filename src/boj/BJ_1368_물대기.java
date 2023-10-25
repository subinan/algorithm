package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1368_물대기 {

    private static int[] parent;

    private static class Node implements Comparable<Node> {
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(new Node(N, i, Integer.parseInt(br.readLine())));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i; j++) {
                pq.add(new Node(i, j, Integer.parseInt(st.nextToken())));
            }
        }

        // 크루스칼
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        int answer = 0;
        while (!pq.isEmpty() && cnt < N) {
            Node node = pq.poll();
            if (union(node.from, node.to)) {
                ++cnt;
                answer += node.cost;
            }
        }

        System.out.println(answer);

    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        parent[y] = x;
        return true;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

}