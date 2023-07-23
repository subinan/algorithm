package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_20310_타노스 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        int[] cnt = new int[2];
        for (char c: input) {
            ++cnt[c - '0'];
        }
        cnt[0] /= 2;
        cnt[1] /= 2;

        for (int i = 0; i < input.length; i++) {
            if (input[i] == '1' && cnt[1] > 0) {
                input[i] = ' ';
                --cnt[1];
            }
        }
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] == '0' && cnt[0] > 0) {
                input[i] = ' ';
                --cnt[0];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c: input) {
            if (c != ' ') sb.append(c);
        }

        System.out.println(sb);
    }

}
