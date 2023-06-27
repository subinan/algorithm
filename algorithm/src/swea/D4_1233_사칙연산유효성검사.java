package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1233_사칙연산유효성검사 {
	static int N, T = 10;
	static final String oper = "+-*/";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int ans = 1; // 정답을 저장할 변수
			int i = 0; // N개의 정점 정보를 읽어오기 위한 변수
			for (; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				st.nextToken(); // 첫번째 정보는 버린다. 
				if (!check(st.nextToken().charAt(0), st.countTokens())) { // 정점의 데이터와 정점 정보의 길이를 이용하여 유효성을 검사한다.
					// 사칙연산이 유효하지 않으면 ans에 0을 저장하고 반복문을 종료한다. 
					ans = 0;
					break;
				}
					
			}
			for (i++; i < N; i++) { // 반복문이 도중에 종료되었을 경우 남은 정점 정보를 읽어온다. 
				br.readLine();
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n"); // 정답을 출력한다. 
		}
		System.out.print(sb);
	}
	
	public static boolean check(char node, int cnt) {
		if (oper.indexOf(node) != -1) { // 데이터가 수식이고
			if (cnt == 2) return true; // cnt가 2라면 true를 리턴한다. (2개의 정점 정보를 읽었기 때문에 2개의 정보가 남는다.)
		} else { // 데이터가 숫자이고
			if (cnt == 0) return true; // cnt가 0이라면 true를 리턴한다. 
		}
		return false;
	}
}
