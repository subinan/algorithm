package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2493_탑 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<int[]> stack = new Stack<>(); // 탑의 높이와 인덱스 저장
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int top = Integer.parseInt(st.nextToken());
			// 스택이 비거나 자신보다 낮은 탑이 없을 때까지 스택을 pop한다.
			// 자신보다 낮은 탑은 자신에게 가려지기 때문에 현재 위치의 오른쪽의 탑에서 쏜 레이저가 닿을 수 없다.
			// 이 과정을 거치면 스택에는 자신보다 높은 탑이 내림차순으로 저장된다.
			while (!stack.empty() && stack.peek()[0] < top) {
				stack.pop();
			}
			if (stack.isEmpty()) { // 스택이 비었으면 자신보다 높은 탑이 왼쪽에 없는 것이므로 0을 출력한다.
				sb.append("0 ");
			} else { // 그 외에는 스택의 가장 위에 있는 탑의 인덱스를 출력한다.
				sb.append(stack.peek()[1]).append(" ");
			}
			stack.push(new int[]{top, i});
		}

		System.out.println(sb);
	}
	
}
