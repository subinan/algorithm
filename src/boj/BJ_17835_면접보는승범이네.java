package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_17835_면접보는승범이네 {

    private static final long INF = (long) 1e18;
    private static Node[] adj;

    private static class Node implements Comparable<Node> {
        int to;
        long dist;
        Node next;
        public Node(int to, long dist) {
            this.to = to;
            this.dist = dist;
            this.next = null;
        }
        public Node(int to, long dist, Node next) {
            this.to = to;
            this.dist = dist;
            this.next = next;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        adj = new Node[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 면접장에서 역으로 방문하기 위해 방문 배열을 거꾸로 저장하기
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            adj[v] = new Node(u, c, adj[v]);
        }

        int[] interview = new int[K];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            interview[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        dijkstra(interview, adj);
    }

    // 다익스트라
    private static void dijkstra(int[] startArr, Node[] adj) {
        int n = adj.length;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        // 시작 정점 여러개
        for (int start: startArr) {
            pq.add(new Node(start, 0));
            dist[start] = 0;
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.to] < cur.dist) continue;

            for (Node next = adj[cur.to]; next != null; next = next.next) {
                if (dist[next.to] > dist[cur.to] + next.dist) {
                    dist[next.to] = dist[cur.to] + next.dist;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        // 정답 구하기
        long max = 0;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] > max) {
                max = dist[i];
                idx = i + 1;
            }
        }

        System.out.println(idx);
        System.out.println(max);
    }
}
