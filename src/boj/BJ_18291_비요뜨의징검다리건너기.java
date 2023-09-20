package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_18291_비요뜨의징검다리건너기 {

    private static final int MOD = (int) 1e9 + 7;

    // 2^(n-2)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(pow(2,  n - 2)).append("\n");
        }
        System.out.print(sb);
    }

    public static int pow(int x, int n) {
        if (n <= 0) return 1;
        if (n == 1) return x;

        long res = pow(x, n / 2) % MOD;
        if (n % 2 == 0) return (int) (res * res % MOD);
        else return (int) (res * res % MOD * x % MOD);
    }
}
