package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16926_배열돌리기1 {
	
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

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void rotate() {
		int start = 0;
		int[][] arr = new int[N][M]; // 회전한 배열을 저장할 배열 

		// 회전 그룹수 = Math.min(N, M) / 2
		while (start * 2 < N && start * 2 < M) { // 배열을 전부 돌릴 때 까지
			int r = start, c = start;
			int d = 0;

			while (true) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if (nr < start || nr >= N - start || nc < start || nc >= M - start) { // 회전의 범위를 벗어나면
					d++; // 방향을 바꾼다
					if (d == 4) { // 반시계를 전부 돌았으면 break
						break;
					}
					continue ;
				}
				
				arr[nr][nc] = A[r][c]; // 배열을 회전한다.
				
				r = nr;
				c = nc;
			}
			start++; // 안쪽 배열 회전
		}
		
		A = arr;
	}
}

// https://wiselog.tistory.com/119
