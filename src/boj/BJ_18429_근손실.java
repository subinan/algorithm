package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18429_근손실 {
    private static int N, K, cnt;
    private static int[] kits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        kits = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 500);
        System.out.println(cnt);
    }

    private static void dfs(int depth, int flag, int weight) {
        if (weight < 500) return;

        if (depth == N) {
            ++cnt;
            return;
        }

        weight -= K;
        for (int i = 0; i < N; i++) {
            if ((flag & (1 << i)) != 0) continue;
            dfs(depth + 1, flag | (1 << i), weight + kits[i]);
        }
    }
}
