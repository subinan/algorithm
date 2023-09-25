package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14438_수열과쿼리17 {
    private static final int INF = (int) 1e9;
    private static int N, M, S;
    private static int[] A, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        S = 1;
        while (S < N) {
            S *= 2;
        }

        min = new int[S * 2];
        Arrays.fill(min, INF);
        init();

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(b, c);
            } else {
                sb.append(query(1, S, 1, b, c)).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void init() {
        for (int i = 0; i < N; i++) {
            min[S + i] = A[i];
        }
        for (int i = S - 1; i > 0; i--) {
            min[i] = Math.min(min[i * 2], min[i * 2 + 1]);
        }
    }

    private static int query(int left, int right, int node, int qLeft, int qRight) {
        if (left > qRight || right < qLeft) return INF;
        else if (qLeft <= left && right <= qRight) return min[node];
        else {
            int mid = (left + right) / 2;
            int rLeft = query(left, mid, node * 2, qLeft, qRight);
            int rRight = query(mid + 1, right, node * 2 + 1, qLeft, qRight);
            return Math.min(rLeft, rRight);
        }
    }

    private static void update(int target, int value) {
        int node = S + target - 1;
        min[node] = value;

        node /= 2;
        while (node >= 1) {
            min[node] = Math.min(min[node * 2], min[node * 2 + 1]);
            node /= 2;
        }
    }
}
