package boj;

import java.util.Scanner;

public class BJ_17478_재귀함수가뭔가요 {
	static StringBuilder sb = new StringBuilder();
	
	public static void chatbot(int n, String separator) {
		// basis part
		if (n == 0) { // n이 0이면 재귀함수를 섦명하고 함수를 종료한다.
			sb.append(separator).append("\"재귀함수가 뭔가요?\"\n");
			sb.append(separator).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			sb.append(separator).append("라고 답변하였지.\n");
			return ;
		}
		
		// inductive part
		// 재귀함수를 물어보고
		sb.append(separator).append("\"재귀함수가 뭔가요?\"\n");
		sb.append(separator).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		sb.append(separator).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
		sb.append(separator).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		chatbot(n - 1, separator + "____"); // 자기 자신을 호출하고 (이때 n을 줄이고, 출력 구분자를 설정해준다.)
		// 답변한다.
		sb.append(separator).append("라고 답변하였지.\n");
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		chatbot(N, ""); // 재귀함수를 돌며 챗봇의 응답을 출력한다.
		System.out.println(sb);
		
		in.close();
	}
}
