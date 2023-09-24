package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_21924_도시건설 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // init
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        long c = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Node(from, to, cost));
            c += cost;
        }

        // kruskal
        int cnt = 0;
        while (!pq.isEmpty() && cnt < N - 1) {
            Node node = pq.poll();
            if (union(node.from, node.to)) {
                ++cnt;
                c -= node.cost;
            }
        }

        if (cnt != N - 1) System.out.println(-1);
        else System.out.println(c);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        parent[a] = b;
        return true;
    }

    private static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

}
