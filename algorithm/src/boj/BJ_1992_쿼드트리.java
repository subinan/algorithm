package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1992_쿼드트리 {

	static int N;
	static char[][] input;
	
	static final int[][] dir = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		input = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			input[i] = br.readLine().toCharArray();
		}
		
		compress(0, 0, N);
		
		System.out.println(sb);
	}
	
	// 분할정복
	public static void compress(int r, int c, int size) {
		if (isPossible(r, c, size)) {
			sb.append(input[r][c]);
			return;
		}

		sb.append("(");
		for (int i = 0; i < 4; i++) {
			compress(r + dir[i][0] * size / 2, c + dir[i][1] * size / 2, size / 2);
		}
		sb.append(")");
	}
	
	// 압축여부 확인
	public static boolean isPossible(int r, int c, int size) {
		char chk = input[r][c];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (chk != input[r + i][c + j]) return false;
			}
		}
		return true;
	}
}
