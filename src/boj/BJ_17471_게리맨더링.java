package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17471_게리맨더링 {

	static int N, p[];
	static boolean[][] adj;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		p = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		adj = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			while (cnt-- > 0) {
				int j = Integer.parseInt(st.nextToken()) - 1;
				adj[i][j] = true;
				adj[j][i] = true;
			}
		}
		
		int min = Integer.MAX_VALUE;
		// 부분집합 (공집합, 전체집합은 제외)
		for (int i = 1; i < (1 << N) - 1; i++) {
			// 연결 여부 확인
			if (!isConnected(i)) continue;
			// 인구수 차 구하기
			min = Math.min(min, getDiff(i));
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	// 두 선거구의 인구 차이 구하기
	private static int getDiff(int flag) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) == 0) cnt += p[i];
			else cnt -= p[i];
		}
		return Math.abs(cnt);
	}

	private static boolean isConnected(int flag) {
		boolean[] visited = new boolean[N];
		
		// 1번 선거구를 포함하는 그룹 체크
		bfs(flag, 0, visited);		
		
		// 1번 선거구를 포함하지 않는 그룹 체크
		for (int i = 1; i < N; i++) {
			if ((flag & 1) != (flag >> i & 1)) {
				bfs(flag, i, visited);
				break;
			}
		}
		
		// 모든 선거구 방문 여부 체크
		// 방문하지 않은 선거구가 있다면 연결되지 않은 것
		for (int i = 0; i < N; i++) {
			if (visited[i] == false) return false;
		}
		return true;
	}

	private static void bfs(int flag, int i, boolean[] visited) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(i);
		visited[i] = true;
		
		while (!q.isEmpty()) {
			int from = q.poll();

			for (int to = 0; to < N; to++) {
				if (visited[to] || (flag >> from & 1) != (flag >> to & 1) || !adj[from][to]) continue;
				q.add(to);
				visited[to] = true;
			}
		}
	}
}
