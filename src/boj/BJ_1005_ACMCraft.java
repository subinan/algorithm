package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1005_ACMCraft {

    private static class Node {
        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] D = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
            }

            int[] inDegree = new int[N];
            Node[] adjList = new Node[N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;

                adjList[x] = new Node(y, adjList[x]);
                ++inDegree[y]; // 진입 차수
            }

            int W = Integer.parseInt(br.readLine()) - 1;
            sb.append(topologySort(inDegree, D, adjList, W)).append("\n");
        }

        System.out.print(sb);
    }

    private static long topologySort(int[] inDegree, int[] D, Node[] adjList, int W) {
        int N = adjList.length;

        long[] time = new long[N];
        // 위상 정렬
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) { // 진입 차수가 0이면, 즉 시작하는 노드면 큐에 추가
                queue.add(i);
                time[i] = D[i];
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (Node node = adjList[v]; node != null; node = node.next) { // 인접하는 노드들을 돌면서
                if (--inDegree[node.vertex] == 0) queue.add(node.vertex); // 진입 차수 빼기, 만약 진입 차수가 0이라면 다음 순서에 들어간다!!
                time[node.vertex] = Math.max(time[node.vertex], time[v] + D[node.vertex]); // 시간은 최댓값으로 갱신한다. 이전에 등장하는 모든 건물이 완공되어야 하므로!!
            }
        }
        return time[W];
    }

}
