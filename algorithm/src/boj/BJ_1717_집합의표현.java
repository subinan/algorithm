package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1717_집합의표현 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			char flag = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (flag == '0') { // 합집합이면
				union(a, b);
			} else if (flag == '1') { // 포함 여부를 확인하면  
				if (find(a) == find(b)) sb.append("YES\n");
				else sb.append("NO\n");
			}
		}
		System.out.print(sb);
	}
	
	// https://aeunhi99.tistory.com/65
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) return;
		parent[y] = x;
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
}
