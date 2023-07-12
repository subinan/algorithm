package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2179_비슷한단어 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int prefixSize = 0;
        int sIdx = 0, tIdx = 1;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int size = 0;
                while (size < words[i].length() && size < words[j].length() &&
                        words[i].charAt(size) == words[j].charAt(size)) ++size;
                if (size > prefixSize) {
                    prefixSize = size;
                    sIdx = i;
                    tIdx = j;
                }
            }
        }

        System.out.println(words[sIdx]);
        System.out.println(words[tIdx]);
    }

}
