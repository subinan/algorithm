package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1821_수들의합6_np {

    private static int N, F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = i + 1;
        if (sum(arr) == F) {
            print(arr);
            return;
        }

        while (np(arr)) {
            if (sum(arr) == F) {
                print(arr);
                return;
            }
        }
    }

    private static void print(int[] arr) {
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static boolean np(int[] arr) {
        int i = N - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) --i;

        if (i == 0) return false;

        int j = N - 1;
        while (arr[i - 1] >= arr[j]) --j;

        swap(arr, i - 1, j);

        int k = N - 1;
        while (i < k) swap(arr, i++, k--);

        return true;
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
