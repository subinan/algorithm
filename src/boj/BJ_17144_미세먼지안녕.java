package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17144_미세먼지안녕 {
	
	static int R, C, T, A, total = 0, map[][];
	static Queue<Dust> Q;

	static final int[][] dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

	static class Dust {
		int r, c, amount;
		
		public Dust(int r, int c, int amount) {
			this.r = r;
			this.c = c;
			this.amount = amount;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		Q = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) Q.offer(new Dust(i, j, map[i][j])); // 미세먼지 큐에 넣기
				if (map[i][j] == -1) A = i; // 공기 청정기의 위치(아래 행) 저장
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) total += map[i][j];
			}
		}
		
		bfs();
		
		System.out.println(total); // 남은 미세먼지의 양 출력
	}
	
	public static void bfs() {
		for (int t = 0; t < T; t++) { // T초동안 돌리기
			while (!Q.isEmpty()) {
				Dust cur = Q.poll();
			
				// 미세먼지가 확산
				int cnt = 0; // 확산된 방향의 개수
				for (int k = 0; k < 4; k++) { // 미세먼지는 인접한 네 방향으로 확산
					int nr = cur.r + dir[k][0];
					int nc = cur.c + dir[k][1];
				
					// 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않음
					if (nr < 0 || nr >= R || nc < 0 || nc >= C
							|| (nc == 0 && (nr == A - 1 || nr == A))) continue;
				
					// 확산되는 양은 Ar,c/5이고 소수점은 버린다.
					map[nr][nc] += cur.amount / 5;
					cnt++;
				}
				// (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수)
				map[cur.r][cur.c] -= cur.amount / 5 * cnt;
			}

			// 공기청정기 작동
			airCleaner();
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					// 미세먼지 큐에 넣기
					if (map[i][j] > 0) Q.offer(new Dust(i, j, map[i][j]));
				}
			}
		}
	}
	
	// 공기청정기 순환에 의한 먼지 이동
	public static void airCleaner() {
	    // 사라지는 먼지 계산
		// 공기청정기는 A-1, A에 있다......
	    total -= map[A - 2][0];
	    total -= map[A + 1][0];

	    // 위의 공기 순환 (반시계)
	    for (int i = A - 2; i > 0; i--) map[i][0] = map[i - 1][0]; // 왼쪽줄
	    for (int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1]; // 윗줄
	    for (int i = 1; i <= A - 1; i++) map[i - 1][C - 1] = map[i][C - 1]; // 오른쪽줄
	    for (int i = C - 1; i > 1; i--) map[A - 1][i] = map[A - 1][i - 1]; // 아랫줄
	    map[A - 1][1] = 0; // 정화..

	    // 아래공기 순환 (시계)
	    for (int i = A + 1; i < R - 1; i++) map[i][0] = map[i + 1][0]; // 왼쪽줄
	    for (int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1]; // 아랫줄
	    for (int i = R - 1; i >= A; i--) map[i][C - 1] = map[i - 1][C - 1]; // 오른쪽줄
	    for (int i = C - 1; i > 1; i--) map[A][i] = map[A][i - 1]; // 윗줄
	    map[A][1] = 0; // 정화......
	}
}
