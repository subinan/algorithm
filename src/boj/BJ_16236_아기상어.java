package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16236_아기상어 {
	
	static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point o) {
			int comp = Integer.compare(y, o.y); // 가장 위
			if (comp == 0) { // 같으면 가장 왼쪽
				return Integer.compare(x, o.x);
			}
			return comp;
		}
	}
	
	static class Shark {
		Point p;
		int size/*크기*/, remain/*크기가 증가하기 먹어야하는 물고기의 수*/;
		
		public Shark(int x, int y, int size, int remain) {
			p = new Point(x, y);
			this.size = size;
			this.remain = remain;
		}
	}

	
	static int N, ans;
	
	static int[][] map;
	static Shark babyShark;
	
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 9) {
					babyShark = new Shark(j, i, 2, 2); // 아기 상어 생성
					map[i][j] = 0;
				}
			}
		}
		
		// 엄마 상어에게 도움을 요청할 때까지 물고기 먹기
		while (bfs());
		
		System.out.println(ans); // 시간 출력
	}

	// 엄마 상어에게 도움을 요청하면 false, 물고기를 잡아먹으면 true
	public static boolean bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(babyShark.p); // 현재 상어의 위치 큐에 넣기
		
		boolean[][] visited = new boolean[N][N]; // 방문 체크 배열
		Point next = null; // 상어가 이동할 위치
		int time = 0; // 이동 시간
		
		while (!q.isEmpty()) {
			int size = q.size();
			time++; // 이동 시간 증가
		
			while (size-- > 0) {
				Point p = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dir[i][0];
					int ny = p.y + dir[i][1];
					
					// 범위를 벗어나거나 이미 방문한 위치면 continue
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx]) continue;
					
					// 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없다.
					if (map[ny][nx] <= babyShark.size) {
						q.offer(new Point(nx, ny));
						visited[ny][nx] = true;						
					} 

					// 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
					// 먹을 수 있는 물고기가 여러 마리라면 
					// 1. 가장 가까운 물고기, 
					// 2. 가장 위에 있는 물고기, 
					// 3. 가장 왼쪽에 있는 물고기 순서로 먹는다. 
					if (map[ny][nx] != 0 && map[ny][nx] < babyShark.size) {
						Point comp = new Point(nx, ny);
						if (next == null) next = comp;
						else next = next.compareTo(comp) < 0 ? next : comp; // implements
					}
					
				}
			}
			
			// 먹을 수 있는 물고기가 있다면
			if (next != null) {
				ans += time; // 현재 물고기 위해 이동한 시간을 더한다.
				babyShark.p = next; // 현재 상어의 위치를 물고기의 위치로 옮긴다.
				if (--babyShark.remain == 0) { // 아기 상어가 성장하기 위해 먹어야 하는 물고기의 수를 줄인다.
					++babyShark.size; // 물고기 수를 다 채우면 아기 상어의 크기를 키우고
					babyShark.remain = babyShark.size; // 먹어야 하는 물고기의 수도 갱신한다.
				}
				map[next.y][next.x] = 0;
				return true; // 물고기를 먹으면 true
			} 
		}
		
		return false; // 먹지 못하고 엄마 상어의 도움을 요청하면 false
	}
	
}
