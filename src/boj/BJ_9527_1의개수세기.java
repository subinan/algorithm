package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9527_1의개수세기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long[] dp = getDp(55);

        long ans = getOneCount(B, dp) - getOneCount(A - 1, dp);
        System.out.println(ans);
    }

    /*
     * 이진수에서 1이 등장하는 숫자는 0부터 16까지 다음과 같다.
     * 0 | 1 | 1 2 | 1 2 2 3 | 1 2 2 3 2 3 3 4
     * | 표시를 한 것은 2진수 자릿수를 기준으로 나눴다. 반복되는 패턴을 확인할 수 있다.
     * 2ⁿ일 때 1의 개수 누적합은, 1(1), 2일 때 4(3 + 1), 3일 때 12(8 + 4),
     * 4일 때 32(20 + 12)이다.
     * 따라서, 점화식은 dp[n] = (dp[n - 1] * 2) + 2ⁿ다.
     * 이를 비트 연산으로 치환하면 아래 함수와 같다.
     */
    private static long[] getDp(int len) {
        long[] dp = new long[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = (dp[i - 1] << 1) + (1L << i);
        }
        return dp;
    }

    /*
     * n까지의 1이 몇 개 등장하는지 확인하는 방법은 다음과 같다
     * 숫자가 1100이라고 가정해보자.
     * 이때, 1100은 0 ~ 111까지와, 1000 ~ 1100까지로 나눌 수 있다.
     * 0 ~ 111에서 등장하는 1의 개수는 dp[2]이다.
     * 또한, 1000 ~ 1100에서 가장 왼쪽 자리에 1이 등장할 수 있는 경우는
     * 1100(2) - 1000(2) + 1(2) = 110(2)개, 즉 5개다.
     * 따라서 1의 개수는 12(dp[2]) + 5이다..
     *
     * 그 다음으로 100의 경우를 구해야 한다.
     * 1000 ~ 1100에서 1이 등장하는 수를 구해야 하지만,
     * 이전에 이미 가장 왼쪽 자리의 1의 수는 카운트 했기 때문에 000 ~ 100만 확인하면 된다.
     * 0 ~ 11까지 4개와, 100 1개로 총 5개가 존재한다.
     * 즉, 4(dp[1]) + 1이 된다.
     *
     * 따라서 1100에서 1이 등장하는 횟수는 17 + 5 = 22개가 된다.
     */
    private static long getOneCount(long n, long[] dp) {
        if (n == 0 || n == 1) return n;

        // 이렇게 길이 구해서 하면 더 짧을 줄 알았는데 len 구하는 과정에서 시간 초과..
        // 그래서 자릿수 55로 고정!!
        // int len = 0;
        // while (1 << ++len <= n);

        long cnt = n  & 1;
        for (int i = 54; i > 0; i--) {
            if (n >> i != 0L) {
                n ^= (1L << i);
                cnt += dp[i - 1] + n + 1;
                System.out.println(cnt);
            }
        }
        return cnt;
    }

}
