package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1722_순열의순서 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        long[] factorial = getFactorial(N);

        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        st = new StringTokenizer(br.readLine());
        int no = Integer.parseInt(st.nextToken());

        if (no == 1) {
            // k번째 순열 찾기
            long k = Long.parseLong(st.nextToken()) - 1;

            StringBuilder sb = new StringBuilder();
            for (int i = N; i > 0; i--) {
                int idx = (int) (k / factorial[i - 1]);
                sb.append(list.remove(idx)).append(" ");
                k -= factorial[i - 1] * idx;
            }
            System.out.println(sb);
        } else if (no == 2) {
            // 순열의 번호 찾기
            long cnt = 1;
            for (int i = 1; i <= N; i++) {
                int idx = list.indexOf(Integer.parseInt(st.nextToken()));
                cnt += factorial[N - i] * idx;
                list.remove(idx);
             }
            System.out.println(cnt);
        }
    }

    private static long[] getFactorial(int n) {
        long[] factorial = new long[n];
        factorial[0] = 1;

        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        return factorial;
    }

}
