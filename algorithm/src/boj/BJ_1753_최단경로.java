package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1753_최단경로 {

	static int V, E, K, D[];
	static Node[] adjList;
	
	static final int INF = (int)1e9;
	
	static class Node {
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine()) - 1;
		
		adjList = new Node[V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			// 유향
			adjList[u] = new Node(v, w, adjList[u]);
		}
		
		// 다익스트라
		D = new int[V];
		Arrays.fill(D, INF);
		dijkstra(K);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < V; i++) {
			if (D[i] == INF) sb.append("INF\n");
			else sb.append(D[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((v1, v2)-> v1.weight - v2.weight); // 가중치를 기준으로 최소힙

		D[start] = 0;
		pq.add(new Node(start, 0, null)); // 최소 힙에 정점과 현재 정점까지의 최단 거리를 넣는다. 

		while (!pq.isEmpty()) {
			Node cur = pq.poll(); // 최소 힙에서 최단 거리 정점을 꺼낸다. 

			if (D[cur.vertex] < cur.weight) continue; // 더 짧은 경로를 알고 있다면 continue
			
			// 인접 정점의 거리를 갱신한다.
			for (Node temp = adjList[cur.vertex]; temp != null; temp = temp.next) {
				if (D[temp.vertex] > D[cur.vertex] + temp.weight) {
					D[temp.vertex] = D[cur.vertex] + temp.weight;
					pq.add(new Node(temp.vertex, D[temp.vertex], null)); // 최소 힙에 다음 정점과 다음 정점까지의 최단 거리를 넣는다. 
				}
			}
		}
		
	}
	
	
//	public static void dijkstra(int start) {
//		boolean[] visited = new boolean[V]; // 방문 체크 배열
//		D[start] = 0;
//		
//		int min, minVertex;
//		for (int i = 0; i < V; i++) {
//			min = INF;
//			minVertex = -1;
//			
//			// 방문하지 않은 정점 중 최단거리 정점을 찾는다. 
//			for (int j = 0; j < V; j++) {
//				if (!visited[j] && min > D[j]) { 
//					min = D[j];
//					minVertex = j;
//				}
//			}
//			
//			// 최단거리 정점이 없다면 더 이상 길이 없으므로 반복문을 빠져나온다. 
//			if (minVertex == -1) break;
//			// 방문 체크
//			visited[minVertex] = true;
//			
//			// 인접 정점의 거리를 갱신한다.
//			for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
//				if (!visited[temp.vertex] && D[temp.vertex] > D[minVertex] + temp.weight) {
//					D[temp.vertex] = D[minVertex] + temp.weight;
//				}
//			}
//		}
//	}

}
