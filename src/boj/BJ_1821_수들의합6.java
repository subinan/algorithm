package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1821_수들의합6 {

    private static int N, F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        perm(0, new int[N], 0);
    }

    private static boolean perm(int cnt, int[] arr, int flag) {
        if (cnt == N) {
            if (sum(arr) == F) {
                print(arr);
                return true;
            }
            return false;
        }

        for (int i = 1; i <= N; i++) {
            if ((flag & (1 << i)) != 0) continue;
            arr[cnt] = i;
            // 조건에 맞는 수를 찾았다면 종료
            // 사전 순으로 가장 앞에 오는 것을 찾으면 되기 때문에 이후는 검사할 필요 없다.
            if (perm(cnt + 1, arr, flag | (1 << i))) return true;
        }

        return false;
    }

    private static void print(int[] arr) {
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static int sum(int[] arr) {
        int len = arr.length;

        if (len == 2) return arr[0] + arr[1];
        if (len == 1) return 1;

        int[] next = new int[len - 1];
        for (int i = 0; i < len - 1; i++) {
            next[i] = arr[i] + arr[i + 1];
        }

        return sum(next);
    }
}
