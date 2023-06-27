package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1600_말이되고픈원숭이_2 {

	static int K, W, H, map[][], dp[][][];
	
	static final int[][] knight = {{-2, -1}, {-1, -2}, {-2, 1}, {1, -2}, {2, -1}, {-1, 2}, {2, 1}, {1, 2}}; // 말 이동 방향 배열
	static final int[][] monkey = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 원숭이 이동 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 예외처리
		// https://www.acmicpc.net/board/view/67499
		if (W == 1 && H == 1) {
			System.out.println("0");
			return ;
		}

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[H][W][K + 1];
		bfs();
		
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i <= K; i++) {
			if (dp[H - 1][W - 1][i] != 0) {
				ans = Math.min(ans, dp[H - 1][W - 1][i]);
			}
		}
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, K});
		
		while (!q.isEmpty()) {
				int[] cur = q.poll();
				
				if (cur[0] == H - 1 && cur[1] == W - 1) return; // 목적지에 도착하면 리턴
				
				for (int i = 0; i < 4; i++) { // 4방 이동
					int nr = cur[0] + monkey[i][0];
					int nc = cur[1] + monkey[i][1];
					if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 1 || dp[nr][nc][cur[2]] != 0) continue;
					q.add(new int[] {nr, nc, cur[2]});
					dp[nr][nc][cur[2]] = dp[cur[0]][cur[1]][cur[2]] + 1;
				}
				
				if (cur[2] > 0) { // 뛸 수 있다면
					for (int i = 0; i < 8; i++) { // 말처럼 이동
						int nr = cur[0] + knight[i][0];
						int nc = cur[1] + knight[i][1];
						if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 1 || dp[nr][nc][cur[2] - 1] != 0) continue;
						q.add(new int[] {nr, nc, cur[2] - 1});
						dp[nr][nc][cur[2] - 1] = dp[cur[0]][cur[1]][cur[2]] + 1;
					}
				}
		}
	}
	
}
