package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_12738_가장긴증가하는부분수열3 {

	static int N, A[], C[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		C = new int[N]; // C[k]: 해당(k) 길이를 만족하는 자리(k자리)에 오는 수의 최소값
		int size = 0;
		for (int i = 0; i < N; i++) {
			int pos = Arrays.binarySearch(C, 0, size, A[i]);
			/* index of the search key, if it is contained in the array within the specified range;
			 * otherwise, (-(insertion point) - 1). */
			if (pos >= 0) continue;
			
			int insertPos = Math.abs(pos) - 1;
			C[insertPos] = A[i];
			
			if (insertPos == size) ++size;
		}
		
		System.out.println(size);
	}
	
}
