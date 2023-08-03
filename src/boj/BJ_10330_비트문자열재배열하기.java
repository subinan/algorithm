package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10330_비트문자열재배열하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] bits = new int[N];
        int[] codes = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bits[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            codes[i] = Integer.parseInt(st.nextToken());
        }

        // 정답 배열 만들기
        int[] ansBits1 = new int[N];
        int[] ansBits2 = new int[N];
        int idx = 0;
        boolean flag = true;
        for (int c: codes) {
            for (int i = 0; i < c; i++) {
                ansBits1[idx] = flag ? 1 : 0;
                ansBits2[idx] = flag ? 0 : 1;
                ++idx;
            }
            flag = !flag;
        }

        int ans1 = makeBits(N, bits.clone(), ansBits1);
        int ans2 = makeBits(N, bits.clone(), ansBits2);
        // 최솟값 출력
        System.out.println(Math.min(ans1, ans2));
    }

    // 정답 배열을 만들기 위한 연산의 횟수를 구한다.
    private static int makeBits(int N, int[] bits, int[] ansBits) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (bits[i] != ansBits[i]) {
                int j = 1;
                while (i + j < N && bits[i] == bits[i + j]) ++j;
                if (i + j == N) return (int)1e9; // 불가능하다면 큰 값 반환
                swap(bits, i, j);
                ans += j;
            }
        }
        return ans;
    }

    private static void swap(int[] bits, int i, int j) {
        int tmp = bits[i];
        bits[i] = bits[i + j];
        bits[i + j] = tmp;
    }
}
