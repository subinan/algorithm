package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class D4_1218_괄호짝짓기 {

	static int T = 10, N;
	static Stack<Integer> stack;
	
	static final String opening = "([{<"; // 여는 괄호
	static final String closing = ")]}>"; // 닫는 괄호

	// 카운팅을 사용하여 괄호 체크
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= T; t++) {
			int ans = 1; // 정답을 저장할 변수
			int[] chk = new int[4]; // 카운팅할 배열
			
			N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			for (char c: input.toCharArray()) {
				int open = opening.indexOf(c);
				if (open != -1) { // 여는 괄호일 경우 카운터를 증가시킨다.
					chk[open]++;
				} else { // 닫는 괄호일 경우 카운터가 0일 경우 앞에 여는 괄호가 없는 것이기 때문에 정답을 변경하고 반복문을 종료한다.
					int close = closing.indexOf(c); 
					if (close != -1 && chk[close] > 0) {
						chk[close]--;
					} else {
						ans = 0;
						break ;
						
					}
				}
			}
			
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				cnt += chk[i];
			}
			if (cnt != 0) ans = 0;
			System.out.println("#" + t + " " + ans); // 정답을 출력한다.
		}
		
	}
	
	// 스택을 사용하여 괄호 체크
	public static void main2(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= T; t++) {
			int ans = 1; // 정답을 저장할 변수
			
			N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			stack = new Stack<>();
			for (char c: input.toCharArray()) {
				int open = opening.indexOf(c);
				if (open != -1) { // 여는 괄호일 경우 스택에 넣는다.
					stack.push(open);
				} else { // 닫는 괄호일 경우 스택의 괄호와 비교하여 일치하지 않을 경우 정답을 변경하고 반복문을 종료한다.
					int close = closing.indexOf(c); 
					if (close != -1 && stack.peek() == close) {
						stack.pop();
					} else {
						ans = 0;
						break ;
						
					}
				}
			}
			
			if (!stack.isEmpty()) ans = 0;
			System.out.println("#" + t + " " + ans); // 정답을 출력한다.
		}
		
	}
	
}
