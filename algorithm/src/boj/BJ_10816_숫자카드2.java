package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10816_숫자카드2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			int cnt = upperBound(arr, target) - lowerBound(arr, target);
			
			sb.append(cnt).append(" ");
		}
		System.out.println(sb);
	}
	
	/* lower_bound 함수는 특정 배열과 찾고자 하는 key값이 있을 때 그 key값이 존재한다면 처음으로 등장하는 index를 반환한다. 
	 * 만약 존재하지 않는 값이라면 배열 맨 끝의 index를 반환한다. */
	public static int lowerBound(int[] arr, int target) {
		int start = 0;
		int end = arr.length;
		
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		return (end);
	}
	
	/* upper_bound 함수는 특정 배열과 찾고자 하는 key값이 있을 때 그 key값이 존재한다면 마지막으로 등장하는 index의 다음 값을 반환한다. 
	 * 만약 존재하지 않는 값이라면 배열 맨 끝의 index를 반환한다. */
	public static int upperBound(int[] arr, int target) {
		int start = 0;
		int end = arr.length;
		
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] <= target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		return (end);
	}
}
