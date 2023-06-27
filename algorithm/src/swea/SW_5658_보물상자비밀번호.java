package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SW_5658_보물상자비밀번호 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			char[] input = br.readLine().toCharArray();
			
			Set<String> set = new HashSet<>();
			List<String> list = new ArrayList<>();
			
			int size = N / 4;
			char[] password = new char[size];
			int idx = 0;
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < N; j++) {
					password[idx++] = input[(i + j) % N];
					if (j % size == size - 1) { // 비밀번호가 완성됐다면
						if (set.add(String.valueOf(password))) { // 집합에 추가
							list.add(String.valueOf(password)); // 집합에 추가 성공했다면 중복이 없는 것이므로 리스트에 추가
						}
						// 초기화
						password = new char[size];
						idx = 0;
					}
				}
			}
			
			list.sort((s1, s2) -> s2.compareTo(s1)); // 내림차순 정렬
			sb.append("#").append(t).append(" ").append(Integer.parseInt(list.get(K - 1), 16)).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
