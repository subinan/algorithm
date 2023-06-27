package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_1238_Contact {
	
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
		StringTokenizer st;
		
		int T = 10;
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			adjList = new Node[101];
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Node(to, adjList[from]);
			}
			
			sb.append("#").append(t).append(" ").append(bfs(start)).append("\n");
		}
		System.out.println(sb);
	}

	// 그래프 탐색
	public static int bfs(int start) {
		boolean[] visited = new boolean[101];
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(start);
		visited[start] = true;

		int ans = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			ans = 0; // 더 깊은 노드가 있으므로 ans 최소값으로 설정
			
			while (size-- > 0) { // 같은 깊이의 노드 처리를 위해서 현재 큐의 size만큼 묶어서 처리
				int cur = q.poll();
				ans = Math.max(ans, cur);

				for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
					if (!visited[temp.to]) {
						q.offer(temp.to);
						visited[temp.to] = true;
					}
				}
			}
		}
		
		return ans;
	}
}
