package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1861_정사각형방 {
	static int N;
	static int[][] rooms;
	
	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	
	static int[] ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = new int[2];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					search(i, j, 1, rooms[i][j]); // 여기부터 탐색
				}
			}
			System.out.printf("#%d %d %d\n", t, ans[0], ans[1]);
		}
	}
	
	public static void search(int r, int c, int depth, int start) {
		// 정답 갱신
		if (ans[1] == depth) {
			ans[0] = Math.min(ans[0], start);
		} else if (ans[1] < depth) {
			ans[0] = start;
			ans[1] = depth;
		}
		
		for (int i = 0; i < 4; i++) {
			 int nr = r + dr[i];
			 int nc = c + dc[i];
			 
			 // 배열의 범위 밖으로 나가면 continue
			 if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				 continue ;
			 }
		
			 // 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1이 크면 이동
			 if (rooms[nr][nc] == rooms[r][c] + 1) {
				 search(nr, nc, depth + 1, start);
			 }
		 }
	}
}
