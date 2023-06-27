package boj;

import java.util.Scanner;

public class BJ_1463_1로만들기 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[N + 1];
		
		for (int i = 2; i <= N; i++) { // 정수 i에 대해
			int min = dp[i - 1] + 1; // 1을 뺀다.
			if (i % 3 == 0) min = Math.min(min, dp[i / 3] + 1); // 3으로 나눈다. 
			if (i % 2 == 0) min = Math.min(min, dp[i / 2] + 1); // 2로 나눈다.
			dp[i] = min; // 중 최솟값 기록
		}
		
		System.out.println(dp[N]);
		sc.close();
	}
	
}
