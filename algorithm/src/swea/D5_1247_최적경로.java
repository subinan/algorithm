package swea;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D5_1247_최적경로 {

	static int T, N, min;
	static Point[] coord; // 0: 회사 좌표, 1: 집 좌표, 2~N+2: 고객 좌표
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = getInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = getInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			coord = new Point[N + 2];
			for (int i = 0; i < N + 2; i++) {
				coord[i] = new Point(getInt(st.nextToken()), getInt(st.nextToken()));
			}
			
			min = Integer.MAX_VALUE;
			perm(0, 0, 0, 0);
			sb.append("#" + t + " " + min + "\n");
		}
		
		System.out.println(sb);
	}
	
	// 고객 방문 순서로 순열을 만든다.
	public static void perm(int cnt, int flag, int prev/*이전 방문 좌표 -> 거리를 구하기 위해서 필요*/, int distance) {
		if (distance > min) return; // 가지치기
		
		if (cnt == N) { // 모든 고객을 방문했다면
			distance += getDistance(prev, 1); // 마지막 고객의 좌표에서 집까지의 거리를 더한다. 
			min = Math.min(min, distance); // 최적 경로 갱신
			return; // 종료
		}
		
		for (int i = 2; i < N + 2; i++) {
			if ((flag & 1 << i) != 0) continue; // 이미 방문했다면 continue
			perm(cnt + 1, flag | 1 << i, i, distance + getDistance(prev, i));
		}
	}
	
	// 두 좌표 사이의 거리를 구해서 반환한다.
	public static int getDistance(int d1, int d2) {
		return Math.abs(coord[d1].x - coord[d2].x) + Math.abs(coord[d1].y - coord[d2].y);
	}
	
	public static int getInt(String num) {
		return Integer.parseInt(num);
	}
}
