package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_1697_숨바꼭질 {
	
	static final int MAX = 100000;
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 
		 System.out.println(solve(sc.nextInt(), sc.nextInt()));
		 
		 sc.close();
	}
	
	public static int solve(int n, int k) {
		boolean[] visited = new boolean[MAX + 1];
		Queue<Integer> q = new LinkedList<>();
		
		int time = 0;
		
		q.add(n); // 현재 좌표 삽입
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int x = q.poll();
				
				if (x < 0 || x > MAX || visited[x]) continue; // 범위를 벗어나거나 이미 방문했으면 continue
				visited[x] = true; // 방문체크
				
				if (x == k) return time;

				q.add(x - 1); // 걷기-> X-1로 이동
				q.add(x + 1); // 걷기-> X+1로 이동
				q.add(x * 2); // 순간이동-> X*2로 이동
			}
			
			++time;
		}
		
		return -1;
	}
	
}
