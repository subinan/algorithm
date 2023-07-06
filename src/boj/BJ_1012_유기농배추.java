package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1012_유기농배추 {
	
	private static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private static int N, M, K;
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[M][N];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[x][y] = 1;
			}
			
			int ans = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						bfs(map, i, j);
						++ans;
					}
				}
			}
			System.out.println(ans);
		}
	}
	
	private static void bfs(int[][] map, int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dir[i][0];
				int ny = cur[1] + dir[i][1];
				
				if (nx < 0 || nx >= M || ny < 0 || ny >= N || map[nx][ny] != 1) continue ;
				map[nx][ny] = 2;
				q.add(new int[] {nx, ny});
			}
		}
	}
	
}
