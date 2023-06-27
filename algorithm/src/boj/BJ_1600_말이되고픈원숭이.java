package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1600_말이되고픈원숭이 {

	static int K, W, H, map[][];
	
	static final int[][] knight = {{-2, -1}, {-1, -2}, {-2, 1}, {1, -2}, {2, -1}, {-1, 2}, {2, 1}, {1, 2}}; // 말 이동 방향 배열
	static final int[][] monkey = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 원숭이 이동 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, K});

		boolean[][][] visited  = new boolean[H][W][K + 1]; // 장애물 뛰어넘은 횟수만큼 방문 체크 배열 생성
		
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			
			while (size-- > 0) {
				int[] cur = q.poll();
				
				if (cur[0] == H - 1 && cur[1] == W - 1) return cnt; // 목적지에 도착하면 움직인 횟수 반환
				
				for (int i = 0; i < 4; i++) { // 4방 이동
					int nr = cur[0] + monkey[i][0];
					int nc = cur[1] + monkey[i][1];
					if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 1 || visited[nr][nc][cur[2]]) continue;
					q.add(new int[] {nr, nc, cur[2]});
					visited[nr][nc][cur[2]] = true; // 방문 체크
				}
				
				if (cur[2] > 0) { // 뛸 수 있다면
					for (int i = 0; i < 8; i++) { // 말처럼 이동
						int nr = cur[0] + knight[i][0];
						int nc = cur[1] + knight[i][1];
						if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 1 || visited[nr][nc][cur[2] - 1]) continue;
						q.add(new int[] {nr, nc, cur[2] - 1});
						visited[nr][nc][cur[2] - 1] = true; // 방문 체크
					}
				}
			}
			++cnt;
		}
		return -1; // 갈 수 없다면 -1 반환
		
	}
	
}
