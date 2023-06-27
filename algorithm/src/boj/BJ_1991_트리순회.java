package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1991_트리순회 {

	static int N;
	static Node[] nodes;
	static StringBuilder sb;
	
	static class Node { // 노드 클래스
		char left;
		char right;
		
		Node(char left, char right) {
			this.left = left;
			this.right = right;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[st.nextToken().charAt(0) - 'A'] = new Node(st.nextToken().charAt(0), st.nextToken().charAt(0));
		}

		sb = new StringBuilder();
		preOrder('A'); // 전위 순회
		sb.append("\n");
		inOrder('A'); // 중위 순회
		sb.append("\n");
		postOrder('A'); // 후위 순회
		
		System.out.println(sb);
	}
	
	public static void preOrder(char c) {
		if (c == '.') return ;
		
		sb.append(c);
		preOrder(nodes[c - 'A'].left);
		preOrder(nodes[c - 'A'].right);
	}
	
	public static void inOrder(char c) {
		if (c == '.') return ;

		inOrder(nodes[c - 'A'].left);
		sb.append(c);
		inOrder(nodes[c - 'A'].right);
	}
	
	public static void postOrder(char c) {
		if (c == '.') return ;
		
		postOrder(nodes[c - 'A'].left);
		postOrder(nodes[c - 'A'].right);
		sb.append(c);
	}
}
