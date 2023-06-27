package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_1873_상호의배틀필드 {

	static int H, W, N;
	static char[][] map; // 게임 맵
	static char[] input; // 사용자의 입력 배열
	static int[] tank; // 탱크의 위치, 방향을 기록할 배열

	static String dir = "^v<>"; // 탱크의 방향 표시
	static String moves = "UDLRS"; // 사용자의 입력을 숫자와 매핑
	
	// 상하좌우 이동배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			tank = new int[3];
			for (int h = 0; h < H; h++) {
				map[h] = br.readLine().toCharArray();
				for (int w = 0; w < W; w++) {
					// 탱크의 위치를  찾아서 방향과 함께 기록한다.
					int d = dir.indexOf(map[h][w]);
					if (d != -1) {
						tank[0] = h;
						tank[1] = w;
						tank[2] = d;
						map[h][w] = '.';
					}
				}
			}
		
			N = Integer.parseInt(br.readLine());
			input = br.readLine().toCharArray();
			
			battleField(); // 게임시작
			
			// 맵을 출력한다.
			sb.append("#" + t + " ");
			for (int h = 0; h < H; h++) {
				sb.append(String.valueOf(map[h]) + "\n");
			}
		}
		System.out.println(sb);
	}
	
	public static void battleField() {
		// 사용자의 입력을 수행한다.
		for (int n = 0; n < N; n++) {
			int m = moves.indexOf(input[n]);
			if (m == 4) {
				shoot();
			} else {
				move(m);
			}
		}
		map[tank[0]][tank[1]] = dir.charAt(tank[2]);
	}

	// 포탄이 벽에 닿거나 맵을 벗어날 때까지 발사하며, 벽돌벽과 닿을 경우 벽을 파괴한다.
	public static void shoot() {
		int h = tank[0];
		int w = tank[1];
		int d = tank[2];
		
		while (true) {
			h += dr[d];
			w += dc[d];
			
			if (h < 0 || h >= H || w < 0 || w >= W || map[h][w] == '#') break ;
			
			if (map[h][w] == '*') {
				map[h][w] = '.';
				break ;
			}
		}
	}
	
	// 탱크를 이동시킨다.
	public static void move(int d) {
		int r = tank[0] + dr[d];
		int c = tank[1] + dc[d];
		tank[2] = d;

		if (r < 0 || r >= H || c < 0 || c >= W || map[r][c] != '.') return ;
		
		tank[0] = r;
		tank[1] = c;
	}
}
