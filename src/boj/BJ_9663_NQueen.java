package boj;

import java.util.Scanner;

public class BJ_9663_NQueen {

	static int N, board[], ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		board = new int[N];
		dfs(0);
		
		System.out.println(ans);
		
		sc.close();
	}
	
	public static void dfs(int depth) {
		if (depth == N) {
			++ans;
			return ;
		}
		
		for (int i = 0; i < N; i++) {
			board[depth] = i;
			if (isPromising(depth)) dfs(depth + 1);
		}
	}
	
	public static boolean isPromising(int pos) {
		// 같은 열이거나 대각선에 있으면 안된다.
		for (int i = 0; i < pos; i++) {
			if (board[i] == board[pos] || pos - i == Math.abs(board[pos] - board[i])) {
				return false;
			}
		}
		
		return true;
	}
}
