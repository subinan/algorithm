package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2559_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for (int n = 0; n < N; n++) {
			nums[n] = Integer.parseInt(st.nextToken());
		}
		
		// 초기화
		int l = 0, r;
		int sum = 0;
		for (r = 0; r < K; r++) {
			sum += nums[r];
		}
		
		// 최댓값 구하기
		int max = sum;
		while (r < N) {
			sum -= nums[l++];
			sum += nums[r++];
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}
