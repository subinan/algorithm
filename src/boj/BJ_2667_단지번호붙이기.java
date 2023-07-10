package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class BJ_2667_단지번호붙이기 {
	
	private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '1') {
					list.add(bfs(map, N, i, j));
				}
			}
		}
		Collections.sort(list);
		
		System.out.println(list.size());
		for (int i: list) System.out.println(i);
	}

	private static int bfs(char[][] map, int N,  int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {r, c});
		map[r][c] = '2';
		
		int cnt = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			++cnt;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] != '1') continue;

				q.add(new int[] {nr, nc});
				map[nr][nc] = '2';
			}
		}
		
		return cnt;
	}
	
}
