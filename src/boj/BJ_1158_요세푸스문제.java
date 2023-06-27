package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_1158_요세푸스문제 {

	public static void main(String[] args) {
//		long sTime = System.currentTimeMillis(); // ms 시스템 시간 기반 반환 /1000
//		long sTime2 = System.nanoTime(); // ns jvm 기반 반환 /1000000000
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while (!queue.isEmpty()) {
			for (int i = 0; i < K - 1; i++) {
				queue.add(queue.poll());
			}
			sb.append(queue.poll()).append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb);
		
//		long eTime = System.currentTimeMillis();
//		long eTime2 = System.nanoTime();
//		System.out.println(eTime - sTime);
//		System.out.println(eTime2 - sTime2);
	}

}
