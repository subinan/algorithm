package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16935_배열돌리기3 {

	static int N, M, R;
	static int[][] A;
	
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

		// 배열 회전 연산 수행하기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				rotate1();
				break;
			case 2:
				rotate2();
				break;
			case 3:
				rotate3();
				break;
			case 4:
				rotate4();
				break;
			case 5:
				rotate5();
				break;
			case 6:
				rotate6();
				break;
			default:
				break;
			}
		}

		// 배열 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(A[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	// 1번 연산은 배열을 상하 반전시키는 연산이다.
	public static void rotate1() {
		for (int r = 0; r < N / 2; r++) {
			int[] tmp = A[r];
			A[r] = A[N - 1 - r];
			A[N - 1 - r] = tmp;
		}
	}
	
	// 2번 연산은 배열을 좌우 반전시키는 연산이다.
	public static void rotate2() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M / 2; c++) {
				int tmp = A[r][c];
				A[r][c] = A[r][M - 1 - c];
				A[r][M - 1 - c] = tmp;
			}
		}
	}

	// 3번 연산은 오른쪽으로 90도 회전시키는 연산이다.
	public static void rotate3() {
		int t = N;
		N = M;
		M = t;
		
		int[][] tmp = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				tmp[r][c] = A[M - 1 - c][r];
			}
		}
		A = tmp;
	}

	// 4번 연산은 왼쪽으로 90도 회전시키는 연산이다.
	public static void rotate4() {
		int t = N;
		N = M;
		M = t;
		
		int[][] tmp = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				tmp[r][c] = A[c][N - 1 - r];
			}
		}
		A = tmp;
	}

	/* 1 1 1 1 2 2 2 2
	   1 1 1 1 2 2 2 2
	   1 1 1 1 2 2 2 2
   	   4 4 4 4 3 3 3 3
	   4 4 4 4 3 3 3 3
	   4 4 4 4 3 3 3 3 */
	
	// 5번 연산은 1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동시키는 연산이다.
	public static void rotate5() {
		int[][] tmp = new int[N][M];

		for (int r = 0; r < N / 2; r++) {
			for (int c = 0; c < M / 2; c++) {
				tmp[r][c] = A[r + N / 2][c];
			}
			for (int c = M / 2; c < M; c++) {
				tmp[r][c] = A[r][c - M / 2];
			}
		}
		for (int r = N / 2; r < N; r++) {
			for (int c = 0; c < M / 2; c++) {
				tmp[r][c] = A[r][c + M / 2];
			}
			for (int c = M / 2; c < M; c++) {
				tmp[r][c] = A[r - N / 2][c];
			}
		}
		A = tmp;
	}
	
	// 6번 연산은 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동시키는 연산이다.
	public static void rotate6() {
		int[][] tmp = new int[N][M];

		for (int r = 0; r < N / 2; r++) {
			for (int c = 0; c < M / 2; c++) {
				tmp[r][c] = A[r][c + M / 2];
			}
			for (int c = M / 2; c < M; c++) {
				tmp[r][c] = A[r + N / 2][c];
			}
		}
		for (int r = N / 2; r < N; r++) {
			for (int c = 0; c < M / 2; c++) {
				tmp[r][c] = A[r - N / 2][c];
			}
			for (int c = M / 2; c < M; c++) {
				tmp[r][c] = A[r][c - M / 2];
			}
		}
		A = tmp;
	}
}
