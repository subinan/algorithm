package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4012_요리사 {

	static int T, N, min;
	static int[][] S;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			S = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			comb(0, 0, 0);
			
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.print(sb);
	}
	
	// comb
	// nCn/2
	public static void comb(int cnt, int start, int flag) {
		if (cnt == N / 2) {
			getSynergy(flag);
			return ;
		}
		
		for (int i = start; i < N; i++) {
			if ((flag & 1 << i) != 0) continue;
			comb(cnt + 1, i + 1, flag | 1 << i);
		}
	}
	
	// 선택한 식재료를 이용해 음식을 만든 뒤 두 음식 간 맛의 차이를 구한 뒤 최소값을 갱신한다.
	public static void getSynergy(int flag) {
		int a = 0; // a 음식
		int b = 0; // b 음식
		
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) { // 선택한 것 -> a 음식의 재료
				for (int j = 0; j < N; j++) {
					if ((flag & 1 << j) != 0) a += S[i][j];
				}
			} else { // 선택하지 않은 것 -> b 음식의 재료
				for (int j = 0; j < N; j++) {
					if ((flag & 1 << j) == 0) b += S[i][j];
				}
			}
		}
		
		min = Math.min(min, Math.abs(a - b)); // 최소값 갱신
	}
}
