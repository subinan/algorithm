package boj;

import java.util.Scanner;

public class BJ_2023_신기한소수 {
	static int N;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		N = sc.nextInt();
		sb = new StringBuilder();
		
		search(0, 0);
		System.out.print(sb);
		
		sc.close();
	}
	
	public static void search(int num, int digit) {
		if (digit == N) { // N자리수 소수가 완성되면 출력한다.
			sb.append(num).append("\n");
			return ;
		}
		
		for (int i = 0; i <= 9; i++) { // 0~9까지 숫자를 넣으며 신기한 소수를 찾는다.
			int tmp = num * 10 + i;
			if (isPrime(tmp)) {
				search(tmp, digit + 1);
			}
		}
	}
	
	public static boolean isPrime(int num) {
		if (num <= 1) return false; // 소수는 1보다 커야한다.
		
		for (int i = 2; i <= Math.sqrt(num); i++) { // 소수 판별한다.
			if (num % i == 0) return false;
		}
		return true;
	}
	
}
