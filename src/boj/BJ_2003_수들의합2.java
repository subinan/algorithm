package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2003_수들의합2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int l = 0, r = 0;
		int sum = 0;
		int cnt = 0;
		while (l < N) {
			if (r >= N || sum >= M) {
				if (sum == M) cnt++;
				sum -= A[l++];
			}
			else if (sum < M) {
				sum += A[r++];
			}
		}
		
		System.out.println(cnt);
	}
	
}
