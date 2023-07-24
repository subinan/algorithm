package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_10282_해킹 {

    private static final int INF = (int)1e9;

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
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;

            Node[] adjList = new Node[n];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());

                adjList[b] = new Node(a, s, adjList[b]);
            }

            dijkstra(adjList, c);
        }
    }

    private static void dijkstra(Node[] adjList, int c) {
        int[] d = new int[adjList.length];
        Arrays.fill(d, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(c, 0, null));
        d[c] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (d[cur.vertex] > cur.weight) continue;

            for (Node next = adjList[cur.vertex]; next != null; next = next.next) {
                if (d[next.vertex] > d[cur.vertex] + next.weight) {
                    d[next.vertex] = d[cur.vertex] + next.weight;
                    pq.add(new Node(next.vertex, d[next.vertex], null));
                }
            }
        }

        int cnt = 0;
        int max = 0;
        for (int i: d) {
            if (i == INF) continue;
            ++cnt;
            max = Math.max(max, i);
        }

        System.out.println(cnt + " " + max);
    }

}
