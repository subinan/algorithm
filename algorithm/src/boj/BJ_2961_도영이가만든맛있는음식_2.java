package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2961_도영이가만든맛있는음식_2 {

	static int N;
	static int[] S, B; // 신맛 배열, 쓴맛 배열
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		B = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		comb(0, 0, 1, 0);
		System.out.println(min);
	}
	
	// 조합구하기
	public static void comb(int cnt, int start, int s, int b) {
		if (cnt > 0) min = Math.min(min, Math.abs(s - b)); // 재료가 1개 이상이면 요리

		if (cnt == N) return ; // 모든 재료를 선택했으면 return 
		
		for (int i = start; i < N; i++) { // 재료 선택하기
			comb(cnt + 1, i + 1, s * S[i], b + B[i]);
		}
	}
}
