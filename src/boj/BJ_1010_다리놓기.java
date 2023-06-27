package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1010_다리놓기 {
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		dp = new int[30][30];
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// mCn
			System.out.println(combi(M, N));
		}
	}
	
	// nCr = n-1Cr-1 + n-1Cr
	public static int combi(int n, int r) {
		if (dp[n][r] != 0) return (dp[n][r]);
		if (r == 0 || n == 1 || n == r) return (dp[n][r] = 1);
	
		return (dp[n][r] = (combi(n - 1, r - 1) + combi(n - 1, r)));
	}
	
}
