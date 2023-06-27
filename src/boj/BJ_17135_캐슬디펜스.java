package boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_17135_캐슬디펜스 {
	
	static int N, M, D, max = 0;
	static Point[] archers; // 궁수의 위치 배열
	static ArrayList<Point> enemys; // 적의 위치 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		enemys = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (st.nextToken().equals("1")) {
					enemys.add(new Point(j, i)); // 궁수의 위치 추가하기
				}
			}
		}
		
		archers = new Point[3];
		comb(0, 0, 0); // 궁수 3명을 배치하는 조합 구하기 -> 시뮬레이션
		
		System.out.println(max);
	}
	
	// 궁수 3명을 배치하는 모든 경우의 수 구하기
	public static void comb(int cnt, int start, int flag) {
		if (cnt == 3) {
			playGame();
			return ;
		}

		for (int i = start; i < M; i++) {
			if ((flag & 1 << i) != 0) continue ;
			archers[cnt] = new Point(i, N);
			comb(cnt + 1, i, flag | 1 << i);
		}
	}
	
	// 게임 진행
	public static void playGame() {
		// 적 리스트 복사
		ArrayList<Point> e = new ArrayList<>();
		for (Point enemy: enemys) e.add(new Point(enemy.x, enemy.y));
		
		boolean[] isRemoved = new boolean[e.size()]; // 제거된 적 저장
		int[] removeEnemy = {-1, -1, -1}; // 궁수가 제거한 적의 인덱스
		int cnt/*공격으로 제거한 적의 수*/ = 0, removed/*제거된 모든 적의 수(성 도착 포함)*/ = 0;
		
		while (removed < e.size()) { // 모든 적이 제거될 때까지 반복
			/* 각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다. 
			 * 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 
			 * 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 
			 * 같은 적이 여러 궁수에게 공격당할 수 있다. */
			for (int i = 0; i < 3; i++) {
				int minDis = D + 1;
				for (int j = 0; j < e.size(); j++) {
					if (isRemoved[j]) continue ; // 이미 제거된 경우면 continue
					
					int dis = getDistance(archers[i], e.get(j)); // 궁수와 적 사이의 거리를 구해서
					if (minDis > dis) { // 더 가까운 적이라면 갱신
						minDis = dis;
						removeEnemy[i] = j;
					} else if (dis <= D && minDis == dis) { // 가까운 적이 여럿일 경우 가장 왼쪽의 적 선택
						if (e.get(j).x < e.get(removeEnemy[i]).x) {
							removeEnemy[i] = j;
						}
					}
				}
			}
			
			// 공격받은 적을 게임에서 제거
			for (int i = 0; i < 3; i++) {
				if (removeEnemy[i] > -1 && !isRemoved[removeEnemy[i]]) {
					++cnt;
					++removed;
					isRemoved[removeEnemy[i]] = true;
				}
			}
			
			// 적을 아래로 한 칸 이동
			for (int i = 0; i < e.size(); i++) {
				if (isRemoved[i]) continue ;
				
				if (++e.get(i).y == N) { // 성에 도착했다면 제거
					++removed;
					isRemoved[i] = true;
				}
			}
		}
		max = Math.max(max, cnt);
	}
	
	public static int getDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}
