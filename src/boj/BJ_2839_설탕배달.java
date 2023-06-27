package boj;

import java.util.Scanner;

public class BJ_2839_설탕배달 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int ans = 0;
		
		while (N > 0) { 
			if (N % 5 == 0) { // 설탕이 5로 나눠진다면 5킬로그램 봉지에 담기
				ans += N / 5;
				N %= 5;
			} else { // 설탕이 5로 나눠지지 않는다면 3킬로그램 봉지에 담기
				ans++;
				N -= 3;
			}
		}
		
		if (N != 0) { // N이 0이 아니라면 정확하게 N킬로그램을 만들 수 없는 것
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
		
		sc.close();
	}
	
}
