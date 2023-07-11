package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2583_영역구하기 {
	
	private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[M][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					arr[y][x] = 1;
				}
			}
		}
		
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0) {
					list.add(bfs(arr, M, N, i, j));
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for (int i: list) System.out.print(i + " ");
	}

	private static int bfs(int[][] arr, int R, int C, int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		
		arr[r][c] = -1;
		q.add(new int[] {r, c});

		int size = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			++size;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || arr[nr][nc] != 0) continue;
				
				arr[nr][nc] = -1;
				q.add(new int[] {nr, nc});
			}
		}
		
		return size;
	}
	
}
