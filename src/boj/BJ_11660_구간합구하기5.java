package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11660_구간합구하기5 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] num = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 현재 숫자까지의 누적합을 구한다.
		int[][] sum = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + num[i][j];
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		int x1, y1, x2, y2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			// (3,3)부터 (5,5)까지의 합은
			// (1,1)~(5,5)에서 
			// (1,1)~(2,5)와 (1,1)~(5,2)를 빼고 
			// (1,1)~(2,2)를 더한 것과 같다.
			sb.append((sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1]) + "\n");
		}
		System.out.print(sb);
	}
	
	
	public static void main2(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 현재 행의 누적합을 구한다.
		int[][] num = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				num[i][j] = Integer.parseInt(st.nextToken()) + num[i][j - 1];
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		int x1, y1, x2, y2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			// 1차원 배열의 연속
			int sum = 0;
			for (int x = x1; x <= x2; x++) {
				sum += num[x][y2] - num[x][y1 - 1];
			}
			sb.append(sum + "\n");
		}
		System.out.print(sb);
	}
	
}
