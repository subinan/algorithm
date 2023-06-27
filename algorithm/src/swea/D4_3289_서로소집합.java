package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_3289_서로소집합 {
	
	static int T, N, M, parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			make(); // parents 생성 및 초기화
			sb.append("#").append(t).append(" "); // 출력

			int flag, a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				flag = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if (flag == 0) { // 합집합
					union(a, b);
				} else { // 포함 여부 확인
					if (find(a) == find(b)) sb.append("1");
					else sb.append("0");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void make() {
		parents = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return ;
		
		parents[y] = x;
	}
	
	public static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
}
