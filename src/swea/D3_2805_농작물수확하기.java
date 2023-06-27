package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_2805_농작물수확하기 {
	
	static int[][] farm;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			farm = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = input.charAt(j) - '0';
				}
			}
		
			int sum = 0;
			int k = 0;
			// 마름모로 방문하며 농작물 수확량 더하기
			for (int i = 0; i < N; i++) {
				for (int j = N / 2 - k; j <= N / 2 + k; j++) {
					sum += farm[i][j];
				}

				// 마름모로 방문하기 위해 k값 조절
				if (i < N / 2) k++;
				else k--;
			}
			
			System.out.println("#" + t + " " + sum);
		}
	}

}
