package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_5624_좋은수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // a + b + c = n
        // -> a + b = n - c
        Set<Integer> set = new HashSet<>();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            // a + b = n - c가 존재하면 answer 증가 후 break
            for (int j = 0; j < i; j++) {
                if (set.contains(A[i] - A[j])) {
                    ++answer;
                    break;
                }
            }

            // a + b 결과 set에 넣기
            for (int j = 0; j <= i; j++) {
                set.add(A[i] + A[j]);
            }
        }

        System.out.println(answer);
    }

}
