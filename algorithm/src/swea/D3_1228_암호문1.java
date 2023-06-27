package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class D3_1228_암호문1 {
	static int T = 10, N, M;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			list = new LinkedList<>();
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				if (st.nextToken().equals("I")) {
					int x =Integer.parseInt(st.nextToken());
					int y =Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) {
						list.add(x++, Integer.parseInt(st.nextToken())); // 해당 인덱스에 암호문을 삽입한다.
					}
				}
			}
			
			// 처음 10개의 숫자를 출력한다.
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(list.get(i)).append(" "); // append("\u0000");
			}
			sb.setLength(sb.length() - 1); // 맨 마지막 공백 제거
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
}
