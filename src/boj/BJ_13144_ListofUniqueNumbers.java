package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13144_ListofUniqueNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long ans = 0;
        boolean[] chk = new boolean[100001];

        // l에서 시작할 때 가장 긴 경우의 수를 구한다.
        // ans 개수를 증가한 뒤 l을 이동한다 (시작점 이동)
        // ex) 1 2 3 1 2가 들어오면 1 2 3 / 2 3 1 / 3 1 2 / 1 / 2
        // ans에 더해주는 개수는 1 2 3의 경우 1, 12, 123으로 총 3개다

        int l = 0, r = 0;
        while (l < N) {
            while (r < N && !chk[arr[r]]) {
                chk[arr[r++]] = true;
            }
            ans += r - l;
            chk[arr[l++]] = false;
        }
        System.out.println(ans);
    }

}
