package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1208_Flatten {
	
	static int dumps; // 덤프 횟수
	static int[] boxes; // 상자의 높이를 기록하는 배열
	static final int N = 100;
 
	public static int getMaxIdx() {
		int max = 0, maxIdx = -1;
		
		for (int i = 0; i < N; i++) {
			if (boxes[i] > max) {
				max = boxes[i];
				maxIdx = i;
			}
		}
		return (maxIdx);
	}
	
	public static int getMinIdx() {
		int min = 101, minIdx = -1;
		
		for (int i = 0; i < N; i++) {
			if (boxes[i] < min) {
				min = boxes[i];
				minIdx = i;
			}
		}
		return (minIdx);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			dumps = Integer.parseInt(br.readLine());
			boxes = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}

			int ans = 0;
			while (dumps-- > 0) {
				ans = --boxes[getMaxIdx()] - ++boxes[getMinIdx()];
				if (ans <= 1) break ;
			}

			ans = boxes[getMaxIdx()] - boxes[getMinIdx()];
			System.out.println("#" + t + " " + ans);
		}
	}

}
