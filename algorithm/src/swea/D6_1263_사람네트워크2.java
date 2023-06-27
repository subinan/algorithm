package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D6_1263_사람네트워크2 {

	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && dp[i][j] == 0) dp[i][j] = INF;
				}
			}
			
			floydWarshall(N, dp);
			sb.append("#").append(t).append(" ").append(getMinCC(N, dp)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int getMinCC(int N, int[][] dp) {
		int cc;
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			cc = 0;
			for (int j = 0; j < N; j++) {
				cc += dp[i][j];
			}
			min = Math.min(min, cc);
		}
		return min;
	}

	private static void floydWarshall(int N, int[][] dp) {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
			}
		}
	}
	
}
