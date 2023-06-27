package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D4_3124_최소스패닝트리_2 {

	static int T, V, E;
	static int[][] adj;
	
	static class Edge implements Comparable<Edge> {
		int to, weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			// 인접 행렬 쓰면 메모리 터지는 것 같음...
			List<Edge>[] edges = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				edges[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				edges[a].add(new Edge(b, c));
				edges[b].add(new Edge(a, c));
			}
			
			// 신장 트리 선택 여부 체킹
			boolean[] visited = new boolean[V + 1];
			visited[1] = true; // 임의의 시작점
			
			// 최소힙 사용
			// 임의의 시작점 1과 연결된 모든 간선을 최소힙에 넣는다.
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (Edge edge: edges[1]) {
				pq.offer(edge);
			}
			
			int cnt = 1; // 방문한 정점 수
			long res = 0; // 간선 비용
			while (true) {
				Edge info = pq.poll(); // 최소힙을 사용하여 항상 최소비용 정점을 선택한다.
				
				if (visited[info.to]) continue ; // 만약 방문했다면 continue
				
				visited[info.to] = true; // 방문 체크
				res += info.weight; // 간선 비용 더하기
				
				if (++cnt == V) break; // 모든 정점을 방문했다면 break
				
				for (Edge edge: edges[info.to]) { // 추가한 정점과 연결된 간선 체크
					if (!visited[edge.to]) { // 만약 방문하지 않은 정점이라면
						pq.offer(edge); // 최소힙에 추가
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(res).append("\n"); // 출력
		}
		
		System.out.println(sb);
	}
	
}
