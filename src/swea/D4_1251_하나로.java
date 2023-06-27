package swea;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class D4_1251_하나로 {

	static int T, N, parents[];
	static Point[] islands; // 섬 좌표
	static ArrayList<Edge> edges; // 간선 리스트
	static double E;
	
	static class Edge {
		int start, end;
		double weight;
		
		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
			
			// 가중치: 환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L^2)
			weight = E * Math.pow(Math.sqrt(Math.pow(islands[start].x - islands[end].x, 2) + 
								Math.pow(islands[start].y - islands[end].y, 2)), 2);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1, st2;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 섬의 개수 N
			islands = new Point[N];
			
			st1 = new StringTokenizer(br.readLine()); // 섬들의 X 좌표
			st2 = new StringTokenizer(br.readLine()); // 섬들의 Y 좌표
			for (int i = 0; i < N; i++) {
				// 섬의 좌표 저장
				islands[i] = new Point(Integer.parseInt(st1.nextToken()),
										Integer.parseInt(st2.nextToken()));
			}
			
			// 섬을 연결하는 모든 간선 구하기
			E = Double.parseDouble(br.readLine());
			edges = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					edges.add(new Edge(i, j));
				}
			}
			// 가중치 기준으로 정렬
			Collections.sort(edges, new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					return Double.compare(o1.weight, o2.weight);
				}
			});
			
			// Kruskal
			double ans = 0;
			int cnt = 0;
			make();
			for (Edge edge: edges) { // 모든 간선을 돌며
				if (union(edge.start, edge.end)) { // 섬의 연결 여부 체크
					ans += edge.weight; // 연결되어 있지 않다면 가중치 더하기
					if (cnt++ == N - 1) break; // 모든 섬을 연결했다면 break
				}
			}
			
			// 모든 섬들을 잇는 최소 환경 부담금을 소수 첫째 자리에서 반올림하여 정수 형태로 출력
			sb.append("#").append(t).append(" ").append(Math.round(ans)).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void make() {
		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return false; // 이미 연결되어 있다면 false
		
		parents[y] = x;
		
		return true; // 새로 연결했다면 true
	}
	
	public static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}

}
