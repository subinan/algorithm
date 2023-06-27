package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_3040_백설공주와일곱난쟁이 {
	
	static boolean find;
	static int[] nums;
	
	static final int N = 9;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		nums = new int[N];
		for (int i = 0; i < N; i++) { // 아홉 난쟁이의 모자의 수를 저장한다.
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0, 0);
	}
 
	public static void comb(int cnt, int start, int flag) {
		if (find) return ; // 난쟁이를 찾았으면 종료한다.
		
		if (cnt == 7) { // 일곱명을 고르면
			if (check(flag)) { // 진짜 난쟁인지 확인
				find = true; // 일곱 난쟁이 찾음 표시
				print(flag); // 출력
			}
		}
		
		for (int i = start; i < N; i++) {
			if ((flag & 1 << i) != 0) continue ;
			comb(cnt + 1, i + 1, flag | 1 << i);
		}
	}
	
	// 모자의 합이 100인지 확인
	public static boolean check(int flag) {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			sum += nums[i] * ((flag & 1 << i) >> i); // 선택한 난쟁이의 모자의 수를 더한다.
		}
		return (sum == 100);
	}
	
	// 일곱 난쟁이 출력
	public static void print(int flag) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) sb.append(nums[i]).append("\n"); // 출력
		}
		System.out.print(sb);
	}
}
