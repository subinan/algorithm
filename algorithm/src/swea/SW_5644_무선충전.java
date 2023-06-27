package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5644_무선충전 {
	
	static int N = 10/*지도 크기*/, M/*이동 시간*/, A/*BC의 개수*/;
	static char[][] map;
	static int[] userA, userB; // 사용자 A, B의 이동 정보
	static int[] P; // BC 성능 배열
	
	static final int[][] dir = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			map = new char[N][N];
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 이동 시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수
			
			// 사용자 이동 정보 입력
			userA = new int[M];
			userB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}
			
			// BC 정보 입력
			P = new int[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				bfs(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
						Integer.parseInt(st.nextToken()), i);
				P[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append("#").append(t).append(" ").append(moveUser()).append("\n");
		}

		System.out.print(sb);
	}
	
	// 1. 맵에 배터리 영역 표시하기 -> bitmask
	public static void bfs(int x, int y, int c, int idx) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {y, x, c});
		map[y][x] |= 1 << idx;
		
		boolean[][] visited = new boolean[N][N]; // 방문 체크
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[2] == 0) continue;
			
			for (int i = 1; i <= 4; i++) {
				int ny = cur[0] + dir[i][0];
				int nx = cur[1] + dir[i][1];
				
				if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;

				visited[ny][nx] = true;
				q.offer(new int[] {ny, nx, cur[2] - 1});
				map[ny][nx] |= 1 << idx;
			}
		}
	}
	
	// 2. 사용자의 이동 경로 받아서 배터리 충전량 구하기
	public static int moveUser() {
		int[] a = {0, 0}; // userA 시작 좌표
		int[] b = {N - 1, N - 1}; // userB 시작 좌표
		
		int sum = selectBattery(map[a[0]][a[1]], map[b[0]][b[1]]);
		
		for (int i = 0; i < M; i++) {
			// 플레이어 이동
			a[0] += dir[userA[i]][0];
			a[1] += dir[userA[i]][1];
			b[0] += dir[userB[i]][0];
			b[1] += dir[userB[i]][1];
			
			sum += selectBattery(map[a[0]][a[1]], map[b[0]][b[1]]);
		}

		return(sum);
	}
	
	// 배터리 선택하기
	public static int selectBattery(int flag1, int flag2) {
		int sum = 0;
		int max = 0;
		
		for (int i = 0; i < A; i++) {
			if ((flag1 & 1 << i) != 0) sum = P[i]; // userA가 i번째 BC에 접속 가능하다면 sum = P[i]
			else sum = 0; // userA가 i번째 BC에 접속 불가능하다면 sum = 0
			max = Integer.max(max, sum); // 최대값 갱신
			
			for (int j = 0; j < A; j++) {
				if ((flag2 & 1 << j) != 0) { // userB가 j번째 BC에 접속 가능하다면
					if (sum == 0 || i != j) { // userA가 접속하지 않았거나 다른 BC라면
						sum += P[j]; // P[j]를 더하고
						max = Integer.max(max, sum); // 최대값 갱신
						sum -= P[j]; // 원상복구
					}
				}
			}
		}
		
		return max; // 최대값 반환
	}
}
