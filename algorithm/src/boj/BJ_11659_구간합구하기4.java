package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11659_구간합구하기4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] num = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			num[n] = Integer.parseInt(st.nextToken());
		}

		// 현재 숫자까지의 누적합을 구한다.
		int[] sum = new int[N + 1];
		sum[0] = 0;
		for (int n = 1; n <= N; n++) {
			sum[n] = sum[n - 1] + num[n];
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			// 3부터 5까지의 합은 
			// 1~5의 합에서 1~2의 합을 뺀 것과 같다.
			sb.append(sum[j] - sum[i - 1] + "\n");
		}
		System.out.print(sb);
	}

}
