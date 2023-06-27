package boj;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class BJ_1987_알파벳 {

	static int R, C, max;
	static char[][] board;
	static boolean[] visited; // 알파벳 중복 체크 배열
	
	static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		visited = new boolean[26]; // 알파벳 수만큼 초기화
		dfs(0, 0, 1);
		System.out.println(max);
	}
	
	public static void dfs(int r, int c, int cnt) {
		visited[board[r][c] - 'A'] = true; // 알파벳 방문 체크
		
		for (int i = 0; i < 4; i++) { // 사방탐색
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue; // 배열의 범위를 벗어나면 continue
			
			if (!visited[board[nr][nc] - 'A']) { // 방문하지 않은 알파벳이라면
				dfs(nr, nc, cnt + 1); // 방문
				visited[board[nr][nc] - 'A'] = false; // 방문 체크 해제
			}
		}
		
		max = Math.max(max, cnt); // 최대값 갱신
	}
}
