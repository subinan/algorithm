package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1976_여행가자 {

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				if (st.nextToken().equals("1")) {
					union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		int parent = find(Integer.parseInt(st.nextToken()));
		for (int i = 1; i < M; i++) {
			if (parent != find(Integer.parseInt(st.nextToken()))) {
				System.out.println("NO");
				return ;
			}
		}
		System.out.println("YES");
	}
	
	public static void union(int x, int y) {
		x = find(x); // x의 조상을 찾고
		y = find(y); // y의 조상을 찾고
		
		if (x == y) return ; // 조상이 같다면 return

		parent[y] = x; // 조상 합치기..
	}
	
	public static int find(int x) {
		if (parent[x] == x) return (x); // 루트 노드라면 자신 리턴
		
		// 자신의 조상을 찾아서 부모의 부모에 조상을 넣고 반환
		return (parent[x] = find(parent[x]));
	}
}
