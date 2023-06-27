package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12891_DNA비밀번호 {
	private static String dna; // 입력 받은 dna 문자열을 저장
	private static int[] chk; // 부분문자열의 최소 정보를 저장하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int P = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		dna = br.readLine();
		
		chk = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			chk[i] = Integer.parseInt(st.nextToken());
		}
		
		// 현재 부분문자열에 등장하는 문자를 센다.
		int l = 0, r = S - 1;
		for (int i = l; i <= r; i++) {
			chk[getIdx(dna.charAt(i))]--;
		}

		int ans = 0;
		while (true) {
			if (check()) ans++; // 조건을 충족하면 ans에 1을 더한다.
			if (r + 1 >= P) break; // 인덱스를 벗어나면 반복문을 종료한다.
			// 다음 문자열을 확인하기 위해 문자열을 이동하며 현재 문자열에 있는 문자의 수를 조정한다.
			chk[getIdx(dna.charAt(l++))]++;
			chk[getIdx(dna.charAt(++r))]--;
		}
		System.out.println(ans);
	}

	// 조건을 충족하지 못하면 false, 조건을 충족하면 true를 반환한다.
	private static boolean check() {
		for (int i = 0; i < 4; i++) {
			if (chk[i] > 0) return (false);
		}
		return (true);
	}
	
	// 문자를 받으면 인덱스를 반환한다.
	private static int getIdx(char c) {
		switch (c) {
		case 'A':
			return (0);
		case 'C':
			return (1);
		case 'G':
			return (2);
		case 'T':
			return (3);
		}
		return (-1);
	}
}
