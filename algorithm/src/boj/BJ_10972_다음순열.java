package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10972_다음순열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if (np(arr)) {
			for (int num: arr) {
				System.out.print(num + " ");
			}
			System.out.println();
		} else {
			System.out.println(-1);
		}
	}
	
	public static boolean np(int[] arr) {
		int N = arr.length;
		
		// 1. 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) --i;
		
		if (i == 0) return false;
		
		// 2. 교환할 부분 찾기
		int j = N - 1;
		while (arr[i - 1] >= arr[j]) --j;
		
		// 3. 교환
		swap(arr, i - 1, j);
		
		// 4. 오름차순 정렬
		int k = N - 1;
		while (i < k) swap(arr, i++, k--);
		
		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
