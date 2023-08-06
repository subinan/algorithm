package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1629_곱셈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(mul(A, B, C));
    }

    private static int mul(int a, int b, int c) {
        if (b == 1) return a % c;

        long x = mul(a, b / 2, c);
        if (b % 2 == 0) {
            return (int) ((x * x) % c);
        } else {
            return (int) ((((x * x) % c) * a) % c);
        }
    }

}
