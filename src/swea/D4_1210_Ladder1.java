package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1210_Ladder1 {
	
	static final int N = 100;
	static char[][] ladder; // 사다리 정보를 저장할 2차원 배열
	
	public static int ghostLeg() {
		int x = -1, y = N - 1;
		
		for (int c = 0; c < N; c++) {
			if (ladder[y][c] == '2') {
				x = c;
				break ;
			}
		}
		
		while (y >= 0) {
			// 사다리의 좌우 탐색
			if (x - 1 >= 0 && ladder[y][x - 1] == '1') {
				while (x - 1 >= 0 && ladder[y][x - 1] == '1') {
					x--;
				}
				y--;
			} else if (x + 1 < N && ladder[y][x + 1] == '1') {
				while (x + 1 < N && ladder[y][x + 1] == '1') {
					x++;
				}
				y--;
			} else { // 좌우가 막혀있으면 위로 이동
				y--;
			}
		}
		
		return (x);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 0; t < 10; t++) {
			int tc = Integer.parseInt(br.readLine());
			ladder = new char[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					ladder[r][c] = st.nextToken().charAt(0);
				}
			}
			
			System.out.println("#" + tc + " " + ghostLeg());
		}
	}
}
