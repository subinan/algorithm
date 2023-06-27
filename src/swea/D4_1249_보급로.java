package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class D4_1249_보급로 {

	static int T, N, dp[][];
	static char[][] map;
	
	static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 상하좌우 방향 배열
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
			for (int i = 0; i < N; i++) Arrays.fill(dp[i], INF); // 최댓값으로 초기화
			bfs(); // 맵 탐색
			
			sb.append("#").append(t).append(" ").append(dp[N - 1][N - 1]).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0}); // 0, 0에서 시작
		dp[0][0] = map[0][0] - '0'; // dp 초기값 설정
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) { // 4방 탐색
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || // 범위를 벗어나거나
					dp[nr][nc] <= dp[cur[0]][cur[1]] + (map[nr][nc] - '0')) continue; // 최소값이 아니라면 continue
				dp[nr][nc] = dp[cur[0]][cur[1]] + (map[nr][nc] - '0'); // 최소값 갱신
				q.add(new int[] {nr, nc}); // bfs
			}
		}
	}
	
}
