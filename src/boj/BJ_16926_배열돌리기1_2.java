package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16926_배열돌리기1_2 {
	
	static final int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 반시계 방향으로 회전하기 위한 배열

	static int[][] A;
	static int N, M, R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++) rotate(); // 배열을 R번 회전한다.

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(A[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void rotate() {
		int[][] arr = new int[N][M]; // 회전한 배열을 저장할 배열 
		int cnt = Math.min(N, M) / 2; // 회전 그룹수
		
		for (int i = 0; i < cnt; i++) {
			int r = i, c = i;
			int d = 0;

			while (d < 4) { // 반시계를 전부 돌 때까지 반복
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if (nr < i || nr >= N - i || nc < i || nc >= M - i) { // 회전의 범위를 벗어나면
					d++; // 방향을 바꾼다
					continue ;
				}
				
				arr[nr][nc] = A[r][c]; // 배열을 회전한다.
				
				r = nr;
				c = nc;
			}
		}
		
		A = arr;
	}
}