package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20922_겹치는건싫어 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int l = 0, r = 0;
        int[] cnt = new int[100001];

        while (l < N && r < N) {
            if (r < N && cnt[arr[r]] < K) {
                ++cnt[arr[r++]];
                max = Math.max(max, r - l);
            }
            else if (l < N) --cnt[arr[l++]];
        }

        System.out.println(max);
    }

}
