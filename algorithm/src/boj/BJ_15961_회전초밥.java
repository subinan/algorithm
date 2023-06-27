package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15961_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] sushi = new int[N]; // 벨트 위의 초밥 정보 배열
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int[] selected = new int[d + 1]; // 선택된 초밥 정보 기록 배열
		selected[c] = 1; // 쿠폰을 사용하여 받은 초밥 기록
		int cnt = 1; // 초밥의 종류 수
		
		int l = 0, r = k;
		for (int i = l; i < r; i++) {
			if (selected[sushi[i]] == 0) ++cnt; // 선택되지 않은 초밥이라면 종류 + 1
			++selected[sushi[i]];
		}
		
		int max = cnt;
		while (l < N) { // 원으로 연결
			if (--selected[sushi[l++]] == 0) --cnt; // 왼쪽 초밥을 제외했을 때 해당 초밥이 0개가 된다면 종류 - 1
			if (++selected[sushi[r++]] == 1) ++cnt; // 오른쪽 초밥을 추가했을 때 해당 초밥이 1개가 된다면 새로 등장 종류 + 1
			if (r == N) r = 0; // 인덱스 관리
			max = Math.max(max, cnt); // 최대값 갱신
		}
		System.out.println(max); // 출력
	}
	
}
