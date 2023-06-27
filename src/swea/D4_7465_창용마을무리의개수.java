package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_7465_창용마을무리의개수 {
	
	static int T, N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) parents[i] = i;
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b); // a, b 한 그룹으로
			}
			
			// 자기 자신이 부모면 루트
			// 루트의 수 = 총 그룹 수
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (parents[i] == i) {
					++ans;
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// x, y를 한 그룹으로
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return;
		
		if (x < y) parents[y] = x;
		else parents[x] = y;
	}
	
	// x의 부모 찾기
	public static int find(int x) {
		if (x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
}
