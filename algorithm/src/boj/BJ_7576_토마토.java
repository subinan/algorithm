package boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576_토마토 {

	static int N, M, cnt; // 맵 크기(NxM), 익혀야 하는 토마토의 개수
	static int[][] map;
	
	// bfs
	static boolean[][] visited; // 방문 체크 배열
	static Queue<Point> q = new LinkedList<>();

	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 방향 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		cnt = M * N; // 상자에 들어갈 수 있는 토마토의 개수
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0) {
					--cnt; // 토마토가 없거나 이미 익은 토마토면 익혀야 하는 개수에서 빼기
					if (map[i][j] == 1) { // 익은 토마토라면
						q.offer(new Point(j, i)); // 큐에 추가하고
						visited[i][j] = true; // 방문 체크
					}
				}
			}
		}
		
		if (cnt == 0) { // 익혀야 하는 개수가 0 -> 저장될 때부터 모든 토마토가 익어있음
			System.out.println("0");
		} else { // bfs를 돌며 날짜 구하기
			System.out.println(bfs()); 
		}
	}

	private static int bfs() {
		int day = 0; // 날짜
		
		while (!q.isEmpty()) {
			int size = q.size();
			++day; // day + 1
			
			while (size-- > 0) { // 현재 큐의 모든 토마토의 상하좌우를 익혀야 날짜가 흐른다.
				Point cur = q.poll();
				
				for (int i = 0; i < 4; i++) { // 상하좌우 체크
					int nx = cur.x + dir[i][0];
					int ny = cur.y + dir[i][1];
					
					// 범위를 벗어나거나 이미 방문했으면 continue
					if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx]) continue;
					
					if (map[ny][nx] != -1) { // 토마토가 있다면
						q.offer(new Point(nx, ny)); // 큐에 추가
						visited[ny][nx] = true; // 방문 체크
						
						if (--cnt == 0) return day; // 익혀야 할 토마토의 개수에서 뺀다. 만약 cnt가 0이 되면 현재 날짜 반환
					}
					
				}
			}
		}
		
		return -1;
	}
	
}
