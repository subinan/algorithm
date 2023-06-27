package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2156_포도주시식 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		
		if (n == 1) System.out.println(a[1]);
		else if (n == 2) System.out.println(a[1] + a[2]);
		else {
			int[] dp = new int[n + 1];
			dp[0] = 0;
			dp[1] = a[1];
			dp[2] = a[1] + a[2];
			for (int i = 3; i <= n; i++) {
				dp[i] = Math.max(dp[i - 1], // 현재 포도주를 마시지 않는 경우
						Math.max(dp[i - 2] + a[i], // 이전에 포도주를 마시지 않고 현재 포도주를 마심 (연속 1번)
								dp[i - 3] + a[i - 1] + a[i])); // 이전에 포도주를 마시고 현재 포도주를 마심 (연속 2번)
			}
			
			System.out.println(dp[n]);
		}
	}
	
}
