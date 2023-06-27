package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15683_감시 {
	
	static int N, M, len/*cctv 수*/, min = Integer.MAX_VALUE;
	static char[][] Map;
	static List<int[]> CCTV;
	static int[] select;
	
	// 방향을 나타내는 배열
	// 1번 CCTV의 방향은 i
	// 2번 CCTV의 방향은 i, i+2
	// 3번 CCTV의 방향은 i, i+3
	// 4번 CCTV의 방향은 i, i+2, i+3
	// 5번 CCTV의 방향은 i, i+1, i+2, i+3
	static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 좌하우상

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new char[N][M];
		CCTV = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				Map[i][j] = st.nextToken().charAt(0);
				// CCTV 위치 저장
				if ('1' <= Map[i][j] && Map[i][j] <= '5') CCTV.add(new int[] {i, j}); 
			}
		}
		
		len = CCTV.size();
		select = new int[len];
		perm(0);
		
		System.out.println(min);
	}
	
	// cctv의 숫자만큼 0~4(방향) 택 1 -> 중복순열
	// cctv가 2개라면 경우의 수는 {0,0}, {0,1}, {0,2}, {0,3}, ..., {3,0}, ..., {3,3}
	// => {첫번째 cctv 방향, 두번째 cctv 방향}
	public static void perm(int cnt) {
		if (cnt == len) { // cctv의 방향을 모두 구하면
			char[][] map = new char[N][M]; // 맵 복사
			for (int i = 0; i < N; i++) map[i] = Arrays.copyOf(Map[i], M);
			
			for (int i = 0; i < len; i++) { // 감시영역 표시
				cctv(map, CCTV.get(i)[0], CCTV.get(i)[1], select[i]);
			}
			getMin(map); // 사각지대 최소 크기 구하기
			return ;
		}
		
		// 2번, 5번 CCTV는 돌려도 같은 경우 존재
		// 방향 범위 줄여주기~
		int dirSize = 4;
		if (Map[CCTV.get(cnt)[0]][CCTV.get(cnt)[1]] == '5') dirSize = 1;
		else if (Map[CCTV.get(cnt)[0]][CCTV.get(cnt)[1]] == '2') dirSize = 2;
		
		for (int i = 0; i < dirSize; i++) { // cctv의 방향은 0~dirSize
			select[cnt] = i;
			perm(cnt + 1);
		}
	}
	
	// 각 CCTV별 감시 영역 구하기
	public static void cctv(char map[][], int r, int c, int i) {
		switch (map[r][c]) {
		case '1': // 한쪽 방향
			watch(map, r, c, i);
			break;
		case '2': // 반대 방향
			watch(map, r, c, i);
			watch(map, r, c, i + 2);
			break;
		case '3': // 직각 방향
			watch(map, r, c, i);
			watch(map, r, c, i + 3);
			break;
		case '4': // 세 방향
			watch(map, r, c, i);
			watch(map, r, c, i + 2);
			watch(map, r, c, i + 3);
			break;
		case '5': // 네 방향
			watch(map, r, c, i);
			watch(map, r, c, i + 1);
			watch(map, r, c, i + 2);
			watch(map, r, c, i + 3);
			break;
		default:
			break;
		}
	}

	// CCTV가 감시하는 부분 맵에 표시
	public static void watch(char map[][], int r, int c, int i) {
		i %= 4;
		
		while (true) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			
			// 범위를 벗어나거나 벽에 닿으면 종료
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == '6') break;
			
			r = nr;
			c = nc;
			if (map[r][c] == '0') map[r][c] = '#'; // 빈 칸일 경우 감시 가능 체크
		}
	}
	
	// 사각지대의 최소 크기 구하기
	public static void getMin(char map[][]) {
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') cnt++;
			}
		}
		
		min = Math.min(min, cnt); // 최소 크기 갱신
	}
}
