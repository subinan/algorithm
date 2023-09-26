package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2993_세부분 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int len = input.length();
        String answer = input;

        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    StringBuilder sb = new StringBuilder(input.substring(0, j)).reverse();
                    sb.append(new StringBuilder(input.substring(j, k)).reverse());
                    sb.append(new StringBuilder(input.substring(k, len)).reverse());

                    String str = sb.toString();
                    if (answer.compareTo(str) > 0) answer = str;
                }
            }
        }

        System.out.println(answer);
    }
}
