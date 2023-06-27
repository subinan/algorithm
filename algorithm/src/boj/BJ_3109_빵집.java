package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3109_빵집 {
	
	static int R, C, ans;
	static boolean flag; // 파이프 연결 여부
	static char[][] Map;
	
	static final int[][] dir = {{-1, 1}, {0, 1}, {1, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new char[R][C];
		for (int i = 0; i < R; i++) {
			Map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			flag = false;
			dfs(i, 0); // 모든 행에서 파이프 연결 시도하기
		}
		
		System.out.println(ans);
	}

	public static void dfs(int r, int c) {
		if (c == C - 1) { // 파이프가 연결 되었으면
			ans++; // ans 증가
			flag = true; // flag 설정
			return;
		}
		
		for (int i = 0; i < 3; i++) { // 오른쪽 위, 오른쪽, 오른쪽 아래 순서로
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue; // 범위 체크
			
			if (Map[nr][nc] == '.') { // 갈 수 있는 길이라면
				Map[nr][nc] = 'x'; // 방문 체크
				dfs(nr, nc);
				
				if (flag) break; // 파이프가 연결 되었다면 더 이상 탐색하지 않고 종료
				
//				Map[nr][nc] = '.'; // 여기로는 갈 수 없는 길이라 방문 해제할 필요  x
			}
		}
	}
}
