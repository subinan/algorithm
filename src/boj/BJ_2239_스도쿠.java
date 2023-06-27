package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2239_스도쿠 {

	static int[][] sudoku;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sudoku = new int[9][9];
		for (int i = 0; i < 9; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = input[j] - '0';
			}
		}
		
		solve(0, 0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean solve(int r, int c) {
		// 스도쿠 인덱스 관리
		if (c == 9) {
			c = 0;
			if (++r == 9) { // 스도쿠를 완성했다면 return true
				return true;
			}
		}
		
		// 번호가 주어졌다면 다음 열 검사
		if (sudoku[r][c] != 0) {
			return solve(r, c + 1);
		}
		
		// 번호가 주어지지 않았다면 1~9 대입하기
		for (int i = 1; i <= 9; i++) {
			if (check(r, c, i)) { // 숫자를 넣을 수 있다면 dfs
				sudoku[r][c] = i;
				if (solve(r, c + 1)) { // 스도쿠를 완성했다면 return true
					return true;
				}
				sudoku[r][c] = 0;
			}
		}
		
		return false;
	}

	private static boolean check(int r, int c, int value) {
		// 가로줄 확인
		for (int i = 0; i < 9; i++) {
			if (sudoku[r][i] == value) {
				return false;
			}
		}
		
		// 세로줄 확인
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][c] == value) {
				return false;
			}
		}
		
		// 3x3 확인
		r -= r % 3;
		c -= c % 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sudoku[r + i][c + j] == value) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
