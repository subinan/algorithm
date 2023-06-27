package boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9205_맥주마시면서걸어가기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			// 0: 상근이네 집, 1~n: 편의점, n+1: 펜타포트 락 페스티벌 좌표
			Point[] pos = new Point[n + 2];
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				pos[i] = new Point();
				pos[i].x = Integer.parseInt(st.nextToken());
				pos[i].y = Integer.parseInt(st.nextToken());
			}
			
			sb.append(bfs(n, pos) ? "happy\n" : "sad\n");
 		}
		
		System.out.println(sb);
	}
	
	private static boolean bfs(int n, Point[] pos) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n + 1];
		
		q.add(pos[0]);
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			// 맥주 20개, 1병 당 50미터 => 1000미터 이동 가능
			if (getDistance(cur, pos[n + 1]) <= 1000) return true;
			
			for (int i = 1; i <= n; i++) {
				if (getDistance(cur, pos[i]) > 1000 || visited[i]) continue;
				q.add(pos[i]);
				visited[i] = true;
			}
		}
		
		return false;
	}

	private static int getDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
}
