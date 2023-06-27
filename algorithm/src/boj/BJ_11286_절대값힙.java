package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ_11286_절대값힙 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) { // 비교 연산자 재정의
				int comp = Integer.compare(Math.abs(o1), Math.abs(o2)); // 절댓값이 작은 순서
				if (comp == 0) { // 절대값이 같으면
					return (Integer.compare(o1, o2)); // 값이 작은 순서
				}
				return (comp);
			}
		});
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if (input == 0) { // 0이면 pop
				if (pq.isEmpty()) sb.append("0\n"); // 비어있으면 0
				else sb.append(pq.poll()).append("\n"); // 있으면 값 뽑는다
			} else {
				pq.add(input); // 그 외에는 pq에 넣는다
			}
		}
		
		System.out.println(sb);
	}
	
}
