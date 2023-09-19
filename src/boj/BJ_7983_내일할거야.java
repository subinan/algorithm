package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.Comparator.*;

public class BJ_7983_내일할거야 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] input = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input, comparingInt((int[] i) -> i[1]).reversed());

        // 놀 수 있는 날짜 구하기
        int ans = input[0][1] - input[0][0];
        for (int i = 1; i < n; i++) {
            if (ans < input[i][1]) { // 못 놀고 바로 과제해야함
                ans -= input[i][0];
            } else { // 놀 수 있음 (마감기한 넉넉)
                ans = input[i][1] - input[i][0];
            }
        }

        System.out.println(ans);
    }
}
