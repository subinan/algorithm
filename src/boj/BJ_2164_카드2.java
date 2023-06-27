package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_2164_카드2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		// flag를 이용해 카드를 버리는 과정과, 가장 위에 있는 카드를 가장 아래로 옮기는 과정을 반복한다. 
		boolean flag = true;
		while (queue.size() != 1) { // 원소가 하나만 남을 때까지 반복
			if (flag) queue.poll();
			else queue.add(queue.poll());
			
			flag = !flag;
		}
		
		System.out.println(queue.poll());
		sc.close();
	}
	
}
