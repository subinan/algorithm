package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1543_문서검색 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        char[] find = br.readLine().toCharArray();

        int ans = 0;
        int sLen = str.length, fLen = find.length;
        int sIdx = 0;

        while (sIdx + fLen <= sLen) {
            boolean found = true;

            for (int i = 0; i < fLen; i++) {
                if (str[sIdx + i] != find[i]) {
                    found = false;
                    break;
                }
            }

            if (found) {
                ++ans;
                sIdx += fLen;
            } else {
                ++sIdx;
            }
        }

        System.out.println(ans);
    }

}
