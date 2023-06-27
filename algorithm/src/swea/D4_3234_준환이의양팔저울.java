package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_3234_준환이의양팔저울 {
	
	static int T, N, ans;
	static int[] selected, weight;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			weight = new int[N];
			
			// 무게 추 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = 0;
			selected = new int[N];
			perm(0, 0); // 순열 만들기 -> 부분집합으로 정답 구하기
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 순열
	public static void perm(int cnt, int flag) {
		if (cnt == N) { // 순열을 만들었으면
			subset(0, 0, 0); // 각 추를 양팔저울의 왼쪽에 올릴 것인지 오른쪽에 올릴 것인지를 정한다.
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) continue;
			selected[cnt] = weight[i];
			perm(cnt + 1, flag | 1 << i);
		}
	}

	// 부분집합
	public static void subset(int cnt, int left, int right) {
		// 무게 추를 올릴 때 오른쪽 위에 올라가 있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 더 커져서는 안 된다.
		if (left < right) return ;

		if (cnt == N) { // 무게 추를 모두 올렸으면
			ans++; // 정답 증가
			return ;
		}
		
		// 왼팔에 추가
		subset(cnt + 1, left + selected[cnt], right);
		// 오른팔에 추가
		subset(cnt + 1, left, right + selected[cnt]);
	}
}
