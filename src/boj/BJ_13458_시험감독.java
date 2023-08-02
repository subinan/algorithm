package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13458_시험감독 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long ans = N; // 총감독관 수
        // 부감독관 더하기
        for (int i = 0; i < N; i++) {
            if (A[i] < B) continue; // 총감독관이 모두 관리할 수 있을 경우 건너뜀
            ans += (A[i] - B) / C;
            ans += (A[i] - B) % C == 0 ? 0 : 1;
        }
        System.out.println(ans);
    }

}
