package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1759_암호만들기 {

	static int L, C;
	static char[] input, select;
	
	static final String vowels = "aeiou";
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new char[C];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(input);
		
		select = new char[L];
		comb(0, 0, 0, 0); // 조합 알고리즘
		System.out.print(sb);
	}

	// nCr, n: C, r: L
	public static void comb(int cnt, int start, int v/*모음 수*/, int c/*자음 수*/) {
		if (v > L - 2 || c > L - 1) return ; // 자음, 모음 수 확인
		
		if (cnt == L) { // L개의 문자를 선택하면 
			sb.append(String.valueOf(select)).append("\n"); // 출력
			return ;
		}
		
		for (int i = start; i < C; i++) {
			select[cnt] = input[i];
			
			if (vowels.indexOf(input[i]) == -1) { // 자음이면
				comb(cnt + 1, i + 1, v, c + 1);
			} else { // 모음이면
				comb(cnt + 1, i + 1, v + 1, c);
			}
		}
	}
	
}
