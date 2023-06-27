package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13023_ABCDE {

	static int N, M;
	static boolean flag, visited[];
	static Node[] adjList;
	
	static class Node {
		int to;
		Node next;
		
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			dfs(i, 0); // 현재 노드의 친구 관계 탐색
			visited[i] = false; // 방문체크 해제
		}
		
		if (flag) System.out.println("1"); // ABCDE 관계가 있다면
		else System.out.println("0"); // ABCDE 관계가 없다면
	}

	public static void dfs(int cur, int depth) {
		if (flag) return ; // ABCDE 관계를 찾았으면 탐색하지 않는다.

		visited[cur] = true; // 방문 체크
		
		if (depth == 4) { // ABCED 관계를 찾으면
			flag = true; // flag 설정
			return ; // 종료
		}
		
		// 현재 노드의 모든 인접 리스트를 돈다.
		for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
			if (visited[temp.to]) continue ; // 이미 방문했다면 continue
			
			dfs(temp.to, depth + 1); // dfs
			visited[temp.to] = false; // 방문체크 해제 -> 방문체크를 해제하지 않으면 다른 친구 관계를 구할 때 이미 방문한 경우 dfs를 돌지 않게 된다...
		}
	}
	
}
