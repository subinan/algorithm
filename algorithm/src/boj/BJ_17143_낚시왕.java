package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17143_낚시왕 {

	static int R, C, M, shark[][], map[][], ans;
	
	//  d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미
	static final int[][] dir = {{}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		shark = new int[M + 1][6];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 5; j++) {
				shark[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		setShark(); // 맵에 상어 배치
		
		simul();
		System.out.println(ans);
	}

	private static void simul() {
		for (int c = 1; c <= C; c++) { // 1. 낚시왕이 오른쪽으로 한 칸 이동
			// 2. 상어 낚시 (땅과 가장 가까운 상어)
			int r = 1;
			while (r <= R && map[r][c] == 0) ++r;
			if (r <= R) { // 잡을 상어가 있다면 잡는다.
				ans += shark[map[r][c]][5];
				shark[map[r][c]][0] = -1;
				map[r][c] = 0;
			}
			
			// 3. 상어 이동
			map = new int[R + 1][C + 1];
			for (int i = 1; i <= M; i++) {
				if (shark[i][0] != -1) moveShark(i);
			}
			setShark(); // 맵에 상어 배치
		}
	}

	// 상어 배치	
	private static void setShark() {
		map = new int[R + 1][C + 1];
		
		for (int i = 1; i <= M; i++) {
			if (shark[i][0] == -1) continue;
			if (map[shark[i][1]][shark[i][2]] != 0) {
				if (shark[map[shark[i][1]][shark[i][2]]][5] > shark[i][5]) { // 기존 상어가 더 크다면
					shark[i][0] = -1; // 기존 상어가 현재 상어를 잡아먹는다.
				} else { // 현재 상어가 더 작다면
					shark[map[shark[i][1]][shark[i][2]]][0] = -1; // 현재 상어가 기존 상어를 잡아먹는다.
					map[shark[i][1]][shark[i][2]] = i; // 맵에 표시
				}
			} else {
				map[shark[i][1]][shark[i][2]] = i; // 맵이 비어있다면 그냥 배치
			}
		}
	}

	// i번째 상어 이동
	private static void moveShark(int i) {
		int d = shark[i][4];

		if (d < 3) { // 상하 이동
			int s = shark[i][3] % ((R - 1) * 2);
			while (s-- > 0) {
				if (shark[i][1] == 1) d = 2;
				else if (shark[i][1] == R) d = 1;

				shark[i][1] += dir[d][0];
			}
		} else { // 좌우 이동
			int s = shark[i][3] % ((C - 1) * 2);
			while (s-- > 0) {
				// 가장 자리에 도착하면 방향 전환
				if (shark[i][2] == 1) d = 3;
				else if (shark[i][2] == C) d = 4;
				
				shark[i][2] += dir[d][1];
			}
		}
		shark[i][4] = d;
	}
	
}
