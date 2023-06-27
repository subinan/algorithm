package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_9229_한빈이와SportMart {
	static int TC, N, M, ans;
	static int[] A;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(A);
			
			ans = -1;
			search(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void search(int cnt, int start, int sum) {
		if (cnt == 2) { // 과자를 두 봉지 샀을 경우 최대값을 저장한다.
			ans = Math.max(ans, sum);
			return ;
		}
		
		for (int i = start; i < N; i++) { // 조합이므로 start 값을 조정한다.
			if (A[i] + sum > M) break ; // 과자가 M그램을 초과하면 반복문을 종료한다. 
			search(cnt + 1, i + 1, A[i] + sum); // 다음에 선택할 과자를 탐색한다.
		}
	}
}
