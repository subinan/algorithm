package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4014_활주로건설 {
	
	static int N, X, map[][];
	
	static final int[][] dir = {{1, 0}, {0, 1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			// 세로 체크
			for (int i = 0; i < N; i++) {
				if (check(0, i, 0)) answer++;
			}

			// 가로 체크
			for (int i = 0; i < N; i++) {
				if (check(i, 0, 1)) answer++;
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean check(int r, int c, int flag) {
		int cnt = 1;
		int prevR = map[r][c];
		int prev = 0;
		
		while (true) {
			prev = map[r][c];
			r += dir[flag][0];
			c += dir[flag][1];
			if (r == N || c == N) break;
			int cur = map[r][c];
			
			if (cur == prev) ++cnt; // 활주로 높이가 이전과 같다면 활주로 길이 증가
			else if (Math.abs(cur - prev) > 1) return false; // 활주로 높이 차이가 1보다 크면 건설 불가
			else if (((prevR > prev && prev < cur) && cnt < X * 2) || // 활주로 좌우 연결이 필요하다면 활주로의 길이는 X*2 이상
					((prevR > prev || prev < cur) && cnt < X)) // 활주로를 한쪽만 연결하다면 활주로의 길이는 X 이상
				return false; // 활주로의 길이를 만족하지 못한다면 건설 불가
			else {
				cnt = 1; // 길이 초기화
				prevR = prev; // 이전 활주로의 높이 저장
			}
		}
		
		if (prevR > prev && cnt < X) return false; // 활주로 연결이 필요할 때 길이를 만족하지 못한다면 건설 불가
		return true;
	}
}
