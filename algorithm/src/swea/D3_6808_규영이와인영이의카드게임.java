package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_6808_규영이와인영이의카드게임 {
	static int[] cards1, cards2, cases;
	static boolean[] isSelected;
	static int N = 9, K = 18, winCnt, loseCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			isSelected = new boolean[K + 1];
			cards1 = new int[N];
			for (int i = 0; i < N; i++) { // 규영이의 카드 입력받기
				cards1[i] = Integer.parseInt(st.nextToken());
				isSelected[cards1[i]] = true;
			}
			
			int idx = 0;
			cards2 = new int[N];
			for (int i = 1; i <= K; i++) { // 인영이가 낼 수 있는 카드 저장
				if (!isSelected[i]) {
					cards2[idx++] = i;
				}
			}

			winCnt = 0;
			loseCnt = 0;
			isSelected = new boolean[N];
			cases = new int[N];
			search(0); // 인영이가 낼 수 있는 모든 경우의 수를 탐색
			
			sb.append("#").append(t).append(" ")
				.append(winCnt).append(" ").append(loseCnt).append("\n"); // 출력
		}
		System.out.print(sb);
	}
	
	public static void search(int cnt) {
		if (cnt == N) { // N개의 카드가 선택되면 승패 구하기
			isWin();
			return ;
		}
		
		// 인영이가 낼 수 있는 모든 카드 순서 구하기
		for (int i = 0; i < N; i++) {
			if (isSelected[i] == true) continue;
			
			cases[cnt] = cards2[i];
			
			isSelected[i] = true;
			search(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	public static void isWin() {
		int p1 = 0, p2 = 0; // 규영이와 인영이의 점수를 저장하는 변수
		
		for (int i = 0; i < N; i++) {
			if (cards1[i] > cases[i]) { // 규영이가 낸 카드 점수가 더 높으면
				p1 += (cards1[i] + cases[i]); // 규영이의 점수에 더하기
			} else if (cards1[i] < cases[i]) { // 인영이가 낸 카드 점수가 더 높으면
				p2 += (cards1[i] + cases[i]); // 인영이의 점수에 더하기
			}
		}
		
		if (p1 > p2) winCnt++; // 규영이가 이기면 이긴 횟수 증가
		else if (p1 < p2) loseCnt++; // 규영이가 지면 진 횟수 증가
	}
	
}
