package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4013_특이한자석 {

	static int T, K, red[];
	static boolean magnet[][]; // S: false, N: true
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			
			magnet = new boolean[4][8];
			red = new int[4]; // 각 자석 별 빨간색 화살표가 가리키는 날의 번호
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					if (st.nextToken().charAt(0) == '0') magnet[i][j] = true;
				}
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int magnetNo = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken());
				rotate(magnetNo, r, 1 << magnetNo);
			}
			
			int ans = 0;
			for (int i = 0; i < 4; i++) {
				if (!magnet[i][red[i]]) ans += Math.pow(2, i);
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void rotate(int magnetNo, int r, int flag) {
		// 왼쪽 자석 확인
		if (magnetNo > 0 && (flag & 1 << (magnetNo - 1)) == 0) {
			if (magnet[magnetNo][(red[magnetNo] + 6) % 8] != magnet[magnetNo - 1][(red[magnetNo - 1] + 2) % 8]) {
				rotate(magnetNo - 1, -r, flag | 1 << (magnetNo - 1));
			}
		}
		// 오른쪽 자석 확인
		if (magnetNo < 3 && (flag & 1 << (magnetNo + 1)) == 0) {
			if (magnet[magnetNo][(red[magnetNo] + 2) % 8] != magnet[magnetNo + 1][(red[magnetNo + 1] + 6) % 8]) {
				rotate(magnetNo + 1, -r, flag | 1 << (magnetNo + 1));
			}
		}
		
		// 반시계 방향으로 회전: (red + 1) % 8
		// 시계 방향으로 회전: (red + 7) % 8
		if (r == -1) red[magnetNo] += 1;
		else red[magnetNo] += 7;
		red[magnetNo] %= 8;
	}
	
}
