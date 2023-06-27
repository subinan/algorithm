package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D4_3124_최소스패닝트리 {

	static int T, V, E, parents[];
	static Edge[] edges;
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
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

			// 간선 리스트
			edges = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				edges[i] = new Edge(a, b, c);
			}
			Arrays.sort(edges);
			
			make();
			int cnt = 0;
			long res = 0;
			for (Edge edge: edges) {
				if (union(edge.from, edge.to)) {
					res += edge.weight;
					if (++cnt == V - 1) break;
				}
			}
			
			sb.append("#").append(t).append(" ").append(res).append("\n"); // 출력
		}
		
		System.out.println(sb);
	}

	public static void make() {
		parents = new int[V + 1];
		
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return false;
		
		parents[y] = x;
		return true;
	}
	
	public static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
}
