package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1806_부분합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = 0;
        int sum = 0;
        int ans = N + 1;
        while (true) {
            if (sum < S) {
                if (r == N) break;
                sum += arr[r++];
            } else {
                ans = Math.min(ans, r - l);

                if (l == N) break;
                sum -= arr[l++];
            }
        }

        System.out.println(ans == N + 1 ? 0 : ans);
    }

}
