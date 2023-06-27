package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10971_외판원순회2 {

	static int N, minCost = Integer.MAX_VALUE, W[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0, 0, 0, 1); // 외판원의 순회 여행 경로 순열, 어차피 첫 도시로 돌아오니까 첫번째 도시는 0으로 고정
		System.out.println(minCost);
	}
	
	public static void perm(int prev, int cnt, int cost, int flag) {
		if (cost > minCost) return; // 가지치기
		
		if (cnt == N - 1) {
			// 처음 도시로 돌아가는 길이 있다면 비용 더한 뒤 갱신
			if (W[prev][0] != 0) {
				cost += W[prev][0];
				minCost = Math.min(minCost, cost);
			}
			return;
		}
		
		for (int i = 1; i < N; i++) {
			// 이미 방문했거나 길이 없으면 continue
			if ((flag & 1 << i) != 0 || W[prev][i] == 0) continue ;
			perm(i, cnt + 1, cost + W[prev][i], flag | 1 << i);
		}
	}
	
}
