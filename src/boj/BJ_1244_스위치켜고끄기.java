package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1244_스위치켜고끄기 {
	
	static int N, K;
	static boolean[] switchs; 
	
	// 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다. 
	public static void boy(int n) {
		for (int i = n; i <= N; i += n) { // n의 배수의 스위치 상태를 변경한다.
			switchs[i] = !switchs[i];
		}
	}
	
	// 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 
	// 그 구간에 속한 스위치의 상태를 모두 바꾼다.
	public static void girl(int n) {
		int l = n, r = n; // 구간을 위한 변수를 설정한다.

		switchs[n] = !switchs[n];
		while (--l >= 1 && ++r <= N) { // 스위치의 범위를 벗어나지 않는 동안 
			if (switchs[l] != switchs[r]) { // 좌우가 대칭이 아닐 경우 반복문을 끝낸다.
				break ;
			}
			switchs[l] = !switchs[l];
			switchs[r] = !switchs[r];
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(in.readLine());
		switchs = new boolean[N + 1]; // 번호가 1부터 시작하기 때문에 (N + 1)만큼 스위치를 할당한다.
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= N; i++) {
			if (st.nextToken().equals("1")) { // 1을 입력받으면 스위치를 true로 설정한다.
				switchs[i] = true;
			}
		}

		K = Integer.parseInt(in.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			if (st.nextToken().equals("1")) { // 1을 입력받으면 남학생이 스위치를 조작한다.
				boy(Integer.parseInt(st.nextToken()));
			} else { // 그 외(0)를 입력받으면 여학생이 스위치를 조작한다.
				girl(Integer.parseInt(st.nextToken()));
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) { // 스위치 배열을 돌며 true일 경우 1을, false일 경우 0을 출력한다.
			if (switchs[i]) {
				sb.append("1 ");
			} else {
				sb.append("0 ");
			}
			
			if (i % 20 == 0) { // 20개를 출력하면 줄바꿈한다.
				sb.append("\n");
			}
		}
		
		if (N % 20 == 0) {
			System.out.print(sb);
		} else {
			System.out.println(sb);
		}
	}

}
