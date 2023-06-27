package boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3055_탈출 {

	static int R, C, time[][];
	static char[][] map;
	static Queue<Point> Q;
	static Point p;
	
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		Q = new ArrayDeque<>();

		time = new int[R][C]; // 물이 차오르는 시간 배열
		for (int i = 0; i < R; i++) Arrays.fill(time[i], -1); // 물이 차오르지 않음: -1

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '*') { // 물
					Q.add(new Point(j, i));
					time[i][j] = 0; // 0초에 물이 찬다.
				}
				
				if (map[i][j] == 'S') { // 고슴도치
					p = new Point(j, i);
					map[i][j] = '.'; // 맵을 길로 바꿔줌
				}
			}
		}

		bfs();
		
		int t = findPath();
		if (t == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(t);
		}
	}
	
	// 물을 퍼뜨리며 time 배열에 물이 차오르는 시간을 저장한다. 
	public static void bfs() {
		int t = 1;
		
		while (!Q.isEmpty()) {
			int size = Q.size();
			
			while (size-- > 0) {
				Point cur = Q.poll();
				
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dir[i][0];
					int ny = cur.y + dir[i][1];
					
					if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
					
					if (map[ny][nx] == '.') {
						map[ny][nx] = '*';
						time[ny][nx] = t;
						Q.add(new Point(nx, ny));
					}
				}
			}
			
			++t;
		}
	}
	
	// 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 반환한다. 비버의 굴로 이동할 수 없다면 -1을 반환한다. 
	public static int findPath() {
		boolean[][] visited = new boolean[R][C];
		
		int t = 1;
		
		Q.add(p); // 고슴도치 위치 추가
		visited[p.y][p.x] = true; 
		
		while (!Q.isEmpty()) {
			int size = Q.size();
			
			while (size-- > 0) {
				Point cur = Q.poll();
				
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dir[i][0];
					int ny = cur.y + dir[i][1];
					
					if (ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx]) continue;
					
					if (map[ny][nx] == '.' || (map[ny][nx] == '*' && t < time[ny][nx])) {
						visited[ny][nx] = true;
						Q.add(new Point(nx, ny));
					}
					
					if (map[ny][nx] == 'D') {
						return t;
					}
				}
			}
			++t;
		}
		
		return -1;
	}
	
}
