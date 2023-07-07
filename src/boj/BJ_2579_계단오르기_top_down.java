package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2579_계단오르기_top_down {
	
	private static int[] stairs, dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		stairs = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[N + 1];
		
		dp[1] = stairs[1];
		if (N >= 2) dp[2] = dp[1] + stairs[2];
		
		System.out.println(find(N));
	}
	
	private static int find(int n) {
		if (n == 0 || dp[n] != 0) return dp[n];
		
		return dp[n] = Math.max(find(n - 2), find(n - 3) + stairs[n - 1]) + stairs[n];
	}
	
}
