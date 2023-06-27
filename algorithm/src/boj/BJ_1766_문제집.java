package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1766_문제집 {

	static class Node {
		int vertex;
		Node next;
		
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}
	
	static int N, M, inDegree[];
	static Node[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		inDegree = new int[N + 1];
		adjList = new Node[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			++inDegree[to];
			adjList[from] = new Node(to, adjList[from]);
		}
		
		topologySort();
	}

	private static void topologySort() {
		ArrayList<Integer> list = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) pq.add(i);
		}
		
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			list.add(cur);
			
			for (Node next = adjList[cur]; next != null; next = next.next) {
				if (--inDegree[next.vertex] == 0) pq.add(next.vertex);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int n: list) sb.append(n).append(" ");
		
		System.out.println(sb);
	}
	
}
