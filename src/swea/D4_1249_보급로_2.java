package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class D4_1249_보급로_2 {

	static int T, N, dp[][];
	static char[][] map;
	
	static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			dp = new int[N][N];
			for (int i = 0; i < N; i++) Arrays.fill(dp[i], INF);
			dijkstra();
			
			sb.append("#").append(t).append(" ").append(dp[N - 1][N - 1]).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> (i1[2] - i2[2]));
		pq.add(new int[] {0, 0, map[0][0] - '0'});
		dp[0][0] = map[0][0] - '0';
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (dp[cur[0]][cur[1]] < cur[2]) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (dp[nr][nc] > dp[cur[0]][cur[1]] + (map[nr][nc] - '0')) {
					dp[nr][nc] = dp[cur[0]][cur[1]] + (map[nr][nc] - '0');
					pq.add(new int[] {nr, nc, dp[nr][nc]});
				}
			}
		}
	}
	
	
	
}
