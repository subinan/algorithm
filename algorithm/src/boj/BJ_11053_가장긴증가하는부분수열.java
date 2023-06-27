package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11053_가장긴증가하는부분수열 {

	static int N, A[], LIS[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		LIS = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if (A[j] < A[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			max = Math.max(max, LIS[i]);
		}
		
		System.out.println(max);
	}
	
}
