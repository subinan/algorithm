package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_6808_규영이와인영이의카드게임_2 {
	static int[] cards, cases;
	static boolean[] isSelected;
	static int N = 9, K = 18, winCnt, loseCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			cards = new int[N];
			
			st = new StringTokenizer(br.readLine());
			isSelected = new boolean[K + 1];
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
				isSelected[cards[i]] = true;
			}

			winCnt = 0;
			loseCnt = 0;
			
			cases = new int[N];
			search(0);
			
			sb.append("#").append(t).append(" ")
				.append(winCnt).append(" ").append(loseCnt).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void search(int cnt) {
		if (cnt == N) {
			isWin();
			return ;
		}
		
		for (int i = 1; i <= K; i++) {
			if (isSelected[i] == true) continue;
			
			cases[cnt] = i;
			
			isSelected[i] = true;
			search(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	public static void isWin() {
		int p1 = 0, p2 = 0;
		
		for (int i = 0; i < N; i++) {
			if (cards[i] > cases[i]) {
				p1 += (cards[i] + cases[i]);
			} else if (cards[i] < cases[i]) {
				p2 += (cards[i] + cases[i]);
			}
		}
		
		if (p1 > p2) winCnt++;
		else if (p1 < p2) loseCnt++;
	}
	
}
