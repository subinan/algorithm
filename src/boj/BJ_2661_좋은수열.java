package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2661_좋은수열 {

    private static int N;
    private static String ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        solve(new char[N], 0);
        System.out.println(ans);
    }

    private static boolean solve(char[] str, int cnt) {
        if (cnt == N) {
            ans = new String(str);
            return true;
        }

        for (char i = '1'; i <= '3'; i++) {
            str[cnt] = i;
            if (!check(str, cnt + 1)) continue;

            if (solve(str, cnt + 1)) return true;
        }
        return false;
    }

    private static boolean check(char[] str, int len) {
        // 마지막 문자부터 앞에 반복되는 문자열이 있는지 확인한다.
        // 예를 들어 1212라는 문자열이 있으면 마지막 1, 2를 비교하고, 12, 12를 비교한다.
        // 앞의 문자열은 이미 가능한 문자라는 것을 확인했기 때문에, 마지막에 해당 문자를 추가할 수 있는지 여부를 확인해야 한다.
        for (int i = 1; i  <= len / 2; i++) {
            // 앞의 문자열 인덱스: (len - i * 2) - 1 ~ (len - i) - 1
            // 뒤의 문자열 인덱스: len - i ~ len - 1
            int idx1 = len - i * 2;
            int idx2 = len - i;
            while (idx2 < len) {
                if (str[idx1++] != str[idx2++]) break;
                if (idx2 == len) return false;
            }
        }
        return true;
    }
}

