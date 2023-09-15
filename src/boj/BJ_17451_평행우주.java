package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17451_평행우주 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] v = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        long ans = v[n - 1];

        for (int i = n - 2; i  >= 0; i--) {
            // ans보다 큰 v[i]의 배수 중 최솟값 구하기
            if (ans % v[i] != 0) { // 0이라면 ans가 v[i]의 배수
                ans = (ans / v[i] + 1) * v[i];
            }
        }

        System.out.println(ans);
    }
}
