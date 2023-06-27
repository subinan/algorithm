package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2961_도영이가만든맛있는음식 {

	static int N;
	static int[] S, B, selected; // 신맛 배열, 쓴맛 배열, 선택한 재료 배열
	static long min = Long.MAX_VALUE;
	
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
		
		selected = new int[N];
		comb(0, 0);
		System.out.println(min);
	}
	
	// 조합구하기
	public static void comb(int cnt, int start) {
		if (cnt > 0) cook(cnt); // 재료가 1개 이상이면 요리

		if (cnt == N) return ; // 모든 재료를 선택했으면 return 
		
		for (int i = start; i < N; i++) { // 재료 선택하기
			selected[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
	
	// 신맛과 쓴맛의 최소값 구하기
	public static void cook(int cnt) {
		long s = 1, b = 0;
		
		for (int i = 0; i < cnt; i++) {
			s *= S[selected[i]]; // 신맛의 곱
			b += B[selected[i]]; // 쓴맛의 합
		}
		
		min = Math.min(min, Math.abs(s - b));
	}
}
