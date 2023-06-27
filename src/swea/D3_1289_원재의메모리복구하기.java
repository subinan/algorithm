package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_1289_원재의메모리복구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= tc; t++) {
			char[] bits = in.readLine().toCharArray();
			int ans = bits[0] - '0'; // 첫번째 비트가 1이면 메모리를 한번 수정한 것이다. (초기화 상태는 모든 bit가 0)
			
			for (int i = 1; i < bits.length; i++) { // 두번째 비트부터 돌면서
				if (bits[i] != bits[i - 1]) { // 비트가 변경되었다면
					ans++; // 메모리를 변경한 것이다. ans 증가.
				}
			}
			
			sb.append("#" + t + " ").append(ans + "\n");
		}
		System.out.println(sb);
	}

}
