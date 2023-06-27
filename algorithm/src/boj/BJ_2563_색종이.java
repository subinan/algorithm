package boj;

import java.util.Scanner;

public class BJ_2563_색종이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean[][] paper = new boolean[100][100];
		int N = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					paper[y + j][x + k] = true;
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (paper[j][i]) cnt++;
			}
		}
		System.out.println(cnt);
		
		sc.close();
	}
	
}
