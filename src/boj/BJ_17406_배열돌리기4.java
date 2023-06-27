package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17406_배열돌리기4 {

	static final int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 시계 방향 회전

	static int[][] A, arr, oper; // 입력 받은 배열, 회전 배열, 회전 연산 목록
	static boolean[] visited; // 탐색 시 방문 배열 -> 회전 연산의 인덱스
	static int N, M, K, min = Integer.MAX_VALUE;
	static ArrayList<int[]> perm; // 회전 연산 순열을 저장할 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		
		for (int i = 0; i < N; i++) { // 배열 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		oper = new int[K][3];
		for (int i = 0; i < K; i++) { // 회전 연산 정보 입력
			st = new StringTokenizer(br.readLine());
			oper[i][0] = Integer.parseInt(st.nextToken()) - 1;
			oper[i][1] = Integer.parseInt(st.nextToken()) - 1;
			oper[i][2] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[K];
		arr = new int[N][];
		perm = new ArrayList<>();
		search(0); // 로테이션 순서를 정하는 함수
		
		System.out.println(min); // 최소값 출력
	}
	
	// 배열 회전 함수
	public static void rotate(int r, int c, int s) {
		for (int i = 1; i <= s; i++) {
			int cr = r - i;
			int cc = c - i;
			int d = 0;
			int tmp = arr[cr][cc]; // 시작 값 저장

			while (d < 4) { // 시계 방향으로 회전
				int nr = cr + dir[d][0];
				int nc = cc + dir[d][1];
				
				if (nr < r - i || nr > r + i || nc < c - i || nc > c + i) { // 범위를 벗어나면 방향 변경
					d++;
					continue ;
				}
				
				arr[cr][cc] = arr[nr][nc]; // 배열 회전
				cr = nr;
				cc = nc;
			}
			arr[cr][cc + 1] = tmp; // 시작 값 넣기
		}
	}
	
	public static void search(int cnt) {
		if (cnt == K) { // 순열이 완성되면
			for (int i = 0; i < N; i++) arr[i] = Arrays.copyOf(A[i], M); // 회전을 위한 배열 copy
			for (int[] o: perm) rotate(o[0], o[1], o[2]); // 리스트에 있는 회전 연산 수행
			getAnswer(); // 최소값 갱신
		}
		
		for (int i = 0; i < K; i++) {
			if (visited[i]) continue ;
			
			visited[i] = true; // 방문 체크하고
			perm.add(oper[i]); // 순열에 추가하기
			search(cnt + 1);
			visited[i] = false; // 방문 해제하고
			perm.remove(perm.size() - 1); // 순열에서 빼기
		}
	}
	
	public static int getAnswer() {
		int sum;
		
		for (int i = 0; i < N; i++) {
			sum = 0;
			for (int j = 0; j < M; j++) {
				sum += arr[i][j]; // 행의 합을 구해서
			}
			min = Math.min(min, sum); // 최소값 갱신
		}
		return (min);
	}
}
