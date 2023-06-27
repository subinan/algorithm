package boj;

import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_1541_잃어버린괄호 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		solve(expression);
		
		sc.close();
	}

	public static void solve(String expression) {
		int sum = Integer.MAX_VALUE;
		StringTokenizer minus = new StringTokenizer(expression, "-"); // - 기준으로 문자열 쪼개기
		
		while (minus.hasMoreTokens()) {
			int bracket = 0;
			StringTokenizer plus = new StringTokenizer(minus.nextToken(), "+"); // + 기준으로 문자열 쪼개기
			while (plus.hasMoreTokens()) {
				bracket += Integer.parseInt(plus.nextToken().trim()); // 괄호 내의 합 구하기
			}
			
			if (sum == Integer.MAX_VALUE) sum = bracket; // 처음 등장하면 대입
			else sum -= bracket; // 그 다음부터는 괄호 내의 값을 빼주기
		}
		System.out.println(sum);
	}

}
