package boj;

import java.util.Scanner;

public class BJ_10844_쉬운계단수 {
	
	static final int MOD = 1000000000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 길이가 1인 계단 수 -> 일의 자리 (0으로 시작하는 수는 계단 수가 아니다 !!!)
		int[][] dp = new int[n + 1][10];
		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1; // 일의 자리
		}		
		
		// 길이가 2인 계단 수 ~ 길이가 n인 계단 수
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < 10; j++) { // 일의 자리 숫자(가장 오른쪽 숫자)를 기준으로 체크
				if (j - 1 >= 0) {
					dp[i][j] += dp[i - 1][j - 1];
					dp[i][j] %= MOD;
				}
				if (j + 1 <= 9) {
					dp[i][j] += dp[i - 1][j + 1];
					dp[i][j] %= MOD;
				}
			}
		}

		int total = 0;
		// 해당 길이의 0~9로 끝나는 모든 경우를 더한다.
		for (int i = 0; i < 10; i++) {
			total += dp[n][i];
			total %= MOD;
		}
		
		System.out.println(total); // 출력
		
		sc.close();
	}
	
}
