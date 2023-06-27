package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 9:40~
public class BJ_1194_달이차오른다가자 {

	static int N, M;
	static char[][] map;
	
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		int r = 0, c = 0; // 민식이의 위치
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					r = i;
					c = j;
					map[i][j] = '.';
				}
			}
		}
		
		System.out.println(bfs(r, c));
	}

	private static int bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>(); // r, c, key(bit mask)
		boolean[][][] visited = new boolean[N][M][64]; // 2^6, 보유할 수 있는 열쇠의 모든 경우의 수
		
		q.add(new int[] {r, c, 0});
		visited[r][c][0] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			++cnt;

			int size = q.size();
			while (size-- > 0) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) { // 4방 탐색
					int nr = cur[0] + dir[i][0];
					int nc = cur[1] + dir[i][1];
					// 범위를 벗어나거나 갈 수 없다면 continue
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == '#') continue;
					// 출구에 도착했다면 이동 횟수 리턴
					if (map[nr][nc] == '1') return cnt;
					// 문에 도착했는데 열쇠가 없다면 continue
					if (Character.isUpperCase(map[nr][nc]) && (cur[2] & (1 << (map[nr][nc] - 'A'))) == 0) continue;
					// 열쇠에 도착했다면 열쇠 기록 (비트 마스킹 사용)
					int key = cur[2];
					if (Character.isLowerCase(map[nr][nc])) key |= 1 << (map[nr][nc] - 'a');
					// 이미 방문한 지점이라면 continue
					if (visited[nr][nc][key]) continue;
					// 큐에 추가하고 방문 처리
					q.add(new int[] {nr, nc, key});
					visited[nr][nc][key] = true;
				}
			}
		}
		// 출구에 도착할 수 없다면 -1 리턴
		return -1;
	}
	
}
