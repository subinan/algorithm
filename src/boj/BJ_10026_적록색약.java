package boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_10026_적록색약 {

	static int N;
	static char[][] map;
	static boolean[][] visited;
	
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 색이 다른 영역의 수 찾기 -> BFS
		visited = new boolean[N][N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					cnt++;
					bfs(j, i, map[i][j]);
				}
			}
		}
		System.out.print(cnt + " ");
		
		// 적록 색약은 초록색과 빨간색이 같게 보인다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}

		// 색이 다른 영역의 수 찾기 -> BFS
		visited = new boolean[N][N];
		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					cnt++;
					bfs(j, i, map[i][j]);
				}
			}
		}
		System.out.println(cnt);
	}
	
	public static void bfs(int x, int y, char flag) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx]) continue;

				if (map[ny][nx] == flag) {
					q.add(new Point(nx, ny));
					visited[ny][nx] = true;
				}
			}
		}
	}
	
}
