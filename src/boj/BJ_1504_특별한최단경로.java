package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1504_특별한최단경로 {

    private static int N, E, v1, v2, D[];
    private static final int INF = (int)1e9;
    private static Node[] adjList;

    private static class Node implements Comparable<Node> {
        int vertex;
        int weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new Node[N];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            adjList[a] = new Node(b, c, adjList[a]);
            adjList[b] = new Node(a, c, adjList[b]);
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken()) - 1;
        v2 = Integer.parseInt(st.nextToken()) - 1;

        D = new int[N];

        // v1, v2는 꼭 거치는 정점이다.
        // 0 -> v1 -> v2 -> N 또는 0 -> v2 -> v1 -> N 중 더 가까운 것을 찾는다.
        long res1 = (long) dijkstra(0, v1) + dijkstra(v1, v2) + dijkstra(v2, N - 1);
        long res2 = (long) dijkstra(0, v2) + dijkstra(v2, v1) + dijkstra(v1, N - 1);

        System.out.println((res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2));
    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(D, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, null));
        D[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (D[cur.vertex] < cur.weight) continue;

            for (Node next = adjList[cur.vertex]; next != null; next = next.next) {
                if (D[next.vertex] > D[cur.vertex] + next.weight) {
                    D[next.vertex] = D[cur.vertex] + next.weight;
                    pq.add(new Node(next.vertex, D[next.vertex], null));
                }
            }
        }

        return D[end];
    }

}
