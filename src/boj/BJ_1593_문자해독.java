package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1593_문자해독 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int g = Integer.parseInt(st.nextToken());
        int sLen = Integer.parseInt(st.nextToken());

        String w = br.readLine();
        int[] wCnt = new int[26 * 2]; // 알파벳 개수 저장
        for (int i = 0; i < g; i++) {
            char c = w.charAt(i);
            ++wCnt[c < 'a' ? c - 'A' : 26 + c - 'a']; // 대소문자 구분
        }

        String s = br.readLine();
        int[] sCnt = new int[26 * 2]; // 알파벳 개수 저장
        for (int i = 0; i < g; i++) {
            char c = s.charAt(i);
            ++sCnt[c < 'a' ? c - 'A' : 26 + c - 'a']; // 대소문자 구분
        }

        // 알파벳 개수가 동일하면 경우의 수 +1
        int answer = 0;
        if (isSame(wCnt, sCnt)) ++answer;

        // 슬라이딩 윈도우
        int start = 0, end = g;
        while (end < sLen) {
            char c = s.charAt(start++);
            --sCnt[c < 'a' ? c - 'A' : 26 + c - 'a'];

            c = s.charAt(end++);
            ++sCnt[c < 'a' ? c - 'A' : 26 + c - 'a'];

            if (isSame(wCnt, sCnt)) ++answer; // 알파벳 개수가 동일하면 경우의 수 +1
        }

        System.out.println(answer);
    }

    private static boolean isSame(int[] wCnt, int[] sCnt) {
        for (int i = 0; i < 26 * 2; i++) {
            if (wCnt[i] != sCnt[i]) return false;
        }
        return true;
    }
}
