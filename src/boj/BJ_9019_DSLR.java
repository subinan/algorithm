package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9019_DSLR {

    private static final String DSLR = "DSLR";

    private static class Info {
        int num;
        String ops;

        public Info(int num, String ops) {
            this.num = num;
            this.ops = ops;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(solve(A, B)).append("\n");
        }
        System.out.print(sb);
    }

    private static String solve(int a, int b) {
        Queue<Info> q = new ArrayDeque();
        q.add(new Info(a, ""));

        boolean[] visited = new boolean[10000];
        visited[a] = true;

        while (!q.isEmpty()) {
            Info info = q.poll();

            if (info.num == b) return info.ops;

            int[] result = new int[4];
            result[0] = D(info.num);
            result[1] = S(info.num);
            result[2] = L(info.num);
            result[3] = R(info.num);

            for (int i = 0; i < 4; i++) {
                if (!visited[result[i]]) {
                    q.add(new Info(result[i], info.ops + DSLR.charAt(i)));
                    visited[result[i]] = true;
                }
            }
        }

        return null;
    }

    private static int D(int n) {
        return n * 2 % 10000;
    }

    private static int S(int n) {
        if (n == 0) return 9999;
        return n - 1;
    }

    private static int L(int n) {
        int d1 = n / 1000;
        return n % 1000 * 10 + d1;
    }

    private static int R(int n) {
        int d4 = n % 10;
        return n / 10 + d4 * 1000;
    }

}
