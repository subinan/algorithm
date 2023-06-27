package algo.brute_force;

import java.util.Scanner;

public class NextCombinationTest { // nCr

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int R = sc.nextInt();
		int[] input = new int[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		// 전처리: 원소크기와 같은 크기의 int 배열 P를 생성하여 
		//		 r개 크기만큼 뒤에서 0이 아닌 값(예를 들어 1)로 초기화한다.
		int[] numbers = new int[N];
		for (int i = 1; i <= R; i++) {
			numbers[N - i] = 1;
		}
		
		do {
			for (int i = 0; i < N; i++) { // 조합 완성
				if (numbers[i] == 1) System.out.print(input[i] + " ");
			}
			System.out.println();
		} while (np(numbers));
		
		sc.close();
	}
	
	public static boolean np(int[] numbers) { // numbers 배열의 상태를 근거로 다음 순열 생성, 다음 순열 존재하면 true, 아니면 false
		
		int N = numbers.length;
		// 1. 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i]) --i;
		
		if (i == 0) return false; // 다음 순열을 만들 수 없는 이미 가장 큰 순열의 상태!
		
		// 2. 꼭대기의 바로 앞자리(i - 1)의 값을 크게 만들 교환 값 뒤쪽에서 찾기
		int j = N - 1;
		while (numbers[i - 1] >= numbers[j]) --j;
		
		// 3. i - 1 위치 값과 j 위치 값 교환
		swap(numbers, i - 1, j);
		
		// 4. i 위치부터 맨 뒤까지 수열을 가장 작은 형태의 오름차순으로 변경
		int k = N - 1;
		while (i < k) swap(numbers, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

}
