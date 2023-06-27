package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17472_다리만들기2 {
	
	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}		
	}
	
	static int N, M, S, map[][], path[][], parents[];
	static PriorityQueue<Edge> edges;
	static boolean[][] visited;
	
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬에 번호 붙이기
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && !visited[i][j]) bfs(i, j, ++S);
			}
		}
		
		// 섬을 잇는 모든 다리 구하기
		path = new int[S + 1][S + 1];
		for (int i = 1; i <= S; i++) Arrays.fill(path[i], INF);
		findAllPath();

		// 크루스칼 알고리즘
		// 그래프 간선 오름차순 (PriorityQueue)
		edges = new PriorityQueue<Edge>((e1, e2) -> e1.cost - e2.cost); // 비용 기준
		for (int i = 1; i <= S; i++) {
			for (int j = 1; j <= S; j++) {
				if (path[i][j] != INF) edges.add(new Edge(i, j, path[i][j])); // 경로가 존재하면 pq에 넣기
			}
		}
		
		make();
		int cnt = 0;
		int ans = 0;
		while (!edges.isEmpty()) {
			Edge e = edges.poll();
			if (union(e.from, e.to)) {
				ans += e.cost;
				if (++cnt == S - 1) { // 모든 섬이 연결된다면 다리 길이의 합 출력
					System.out.println(ans);
					return;
				}
			}
		}
		
		System.out.println(-1); // 연결되지 않는다면 -1 출력
		
	}

	// 섬을 잇는 모든 다리 구하기 (섬을 잇는 다리가 여러개라면 제일 작은 값만 기록)
	private static void findAllPath() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						int nr = i, nc = j;
						int dis = -1;
						do {		
							++dis;
							nr += dir[d][0];
							nc += dir[d][1];	
						} while (isRange(nr, nc) && map[nr][nc] == 0);
						// 다리의 길이는 2 이상
						if (isRange(nr, nc) && map[nr][nc] != map[i][j] && dis > 1) {
							path[map[i][j]][map[nr][nc]] = Math.min(dis, path[map[i][j]][map[nr][nc]]);
						}
					}
				}
			}
		}
	}

	// 섬에 번호 붙이기
	private static void bfs(int r, int c, int n) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r, c});
		visited[r][c] = true;
		map[r][c] = n;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if (!isRange(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
				queue.add(new int[] {nr, nc});
				visited[nr][nc] = true;
				map[nr][nc] = n;
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	
	// union-find
	private static void make() {
		parents = new int[S + 1];
		for (int i = 1; i <= S; i++) {
			parents[i] = i;
		}
	}
	
	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return false;
		
		parents[y] = x;
		return true;
	}
	
	private static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
}
