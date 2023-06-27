package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_5607_조합 {

	static final int MOD = 1234567891;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			sb.append("#").append(t).append(" ").append(combination(N, R)).append("\n");
		}
		
		System.out.println(sb);
	}

	// nCr = n!/r!(n-r)!
	private static long combination(int n, int r) {
		long a = 1;
		long b = 1;
		
		// n!/r!
		for (int i = r + 1; i <= n; i++) a = a * i % MOD;
		// (n-r)!
		for (int i = 1; i <= n - r; i++) b = b * i % MOD;
		
		// 페르마의 소정리
		// a^p ≡ a(mod p)
		// a^(p-2) ≡ a^(-1) (mod p)
		long res = power(b, MOD - 2);
		
		return a * res % MOD;
	}

	private static long power(long a, int p) {
		long result = 1;

		while (p > 0) {
			if (p % 2 == 1) result = result * a % MOD;
			a = a * a % MOD;
			p /= 2;
		}
		return result;
	}
	
}
