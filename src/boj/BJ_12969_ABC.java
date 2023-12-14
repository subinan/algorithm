package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://100100e.tistory.com/156
public class BJ_12969_ABC {

    private static int N, K;
    private static boolean[][][][] cache;
    private static char[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cache = new boolean[N][N][N][N * (N - 1) / 2];
        ans = new char[N];

        if (solve(0, 0, 0, 0)) {
            System.out.println(new String(ans));
        } else {
            System.out.println(-1);
        }
    }

    private static boolean solve(int n, int a, int b, int k) {

        if (n == N) {
            if (k == K) return true;
            else return false;
        }

        if (cache[n][a][b][k]) return false;
        cache[n][a][b][k] = true;

        ans[n] = 'A';
        // 마지막에 a가 추가된다면 a보다 작은 것은 없기에 k는 추가되지 않음
        if (solve(n + 1, a + 1, b, k)) return true;

        ans[n] = 'B';
        // 마지막에 b가 추가된다면 이전에 있던 a의 개수만큼 k에 추가됨
        if (solve(n + 1, a, b + 1, k + a)) return true;

        ans[n] = 'C';
        // 마지막에 c가 추가된다면 이전에 있던 a와 b의 개수만큼 k에 추가됨
        if (solve(n + 1, a, b, k + a + b)) return true;

        return false;
    }

}
