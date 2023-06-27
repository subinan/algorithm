package boj;

import java.util.Scanner;

public class BJ_11729_하노이탑이동순서 {
	static StringBuilder sb = new StringBuilder();
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		hanoi(N, 1, 2, 3);
		
		System.out.println(cnt);
		System.out.print(sb);
		sc.close();
	}
	
	public static void hanoi(int n, int s, int t, int e) { // start, temp, end
		if (n == 0) return ;
		
		hanoi(n - 1, s, e, t); // n-1개의 원판을 start에서 temp로 옮기기
		sb.append(s + " " + e + "\n");
		cnt++;
		hanoi(n - 1, t, s, e); // n-1개의 원판을 temp에서 end로 옮기기
	}
}
