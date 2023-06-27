package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1074_Z {
	static int N, R, C, cnt;
	
	static final int[][] dir = {{0, 0}, {0, 1}, {1, 0}, {1, 1}}; // z 모양으로 방문
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		solve(0, 0, (int)Math.pow(2, N));
	}

	public static void solve(int r, int c, int n) {
		if (n == 1) { // n이 1이면
			System.out.println(cnt); // 정답 출력
			return ; // 종료
		}
		
		int n2 = n / 2;
		for (int i = 0; i < 4; i++) { // 2^(N-1) × 2^(N-1)로 4등분 한 후 Z모양으로 방문
			int sr = r + dir[i][0] * n2;
			int er = sr + n2;
			int sc = c + dir[i][1] * n2;
			int ec = sc + n2;
			if (R < sr || R >= er || C < sc || C >= ec) { // 찾는 행렬의 범위를 벗어나면
				cnt += n2 * n2; // cnt만 증가시키고 재귀 x
			} else {
				solve(sr, sc, n2);
			}
		}
	}
}