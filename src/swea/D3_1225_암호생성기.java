package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D3_1225_암호생성기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 0; t < 10; t++) {
			int tc = Integer.parseInt(br.readLine());
			
			Queue<Integer> q = new LinkedList<>(); // 선입선출이므로 큐를 사용한다.
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
			int crypto = 1; // 암호를 만들기 위해 뺄 값을 저장하는 변수
			while (true) {
				int cur = q.poll();
				cur -= crypto++;
				if (crypto > 5) crypto = 1; // 5가 넘어갈 경우 1로 초기화
				if (cur <= 0) { // 0 이하일 경우 반복문을 종료한다.
					q.add(0);
					break;
				}
				q.add(cur);
			}

			System.out.print("#" + tc + " ");
			for (int i = 0; i < 8; i++) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();
		}
	}
	
}
