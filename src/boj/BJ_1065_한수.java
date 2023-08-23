package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1065_한수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N < 100) System.out.println(N);
        else {
            int ans = 99;
            for (int i = 100; i <= N; i++) {
                if (i / 100 - i % 100 / 10 == i % 100 / 10 - i % 10) ++ans;
            }
            System.out.println(ans);
        }
    }
}
