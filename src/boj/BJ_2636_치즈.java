package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2636_치즈 {

	static int R, C, cheese, map[][];
	static boolean[][] visited;
	
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) ++cheese; // 총 치즈의 개수 저장
			}
		}
		
		int ans = 0;
		int cnt = 0;
		while (cheese > 0) {
			visited = new boolean[R][C];
			cnt = bfs(0, 0);
			cheese -= cnt;
			++ans;
		}

		System.out.println(ans);
		System.out.println(cnt);
	}

	private static int bfs(int r, int c) {
		int cnt = 0;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		List<int[]> remove = new ArrayList<>();
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
				if (map[nr][nc] == 1) {
					remove.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				} else {
					q.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
		
		// 치즈 제거
		for (int[] pos: remove) {
			map[pos[0]][pos[1]] = 0;
			++cnt;
		}
		
		return cnt; // 녹은 치즈 수 리턴
	}

}
