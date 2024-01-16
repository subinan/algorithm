package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1099_알수없는문장 {

    private static final int ALPHA_LEN = 26, INF = (int) 1e9;

    private static class Word {
        private String word;
        private int[] cnt;

        public Word(String word) {
            this.word = word;
            cnt = new int[ALPHA_LEN];
            for (int i = 0; i < word.length(); i++) {
                ++cnt[word.charAt(i) - 'a'];
            }
        }

        public boolean isSameAlphabet(String sentence, int start, int end) {
            if (word.length() != end - start) return false;

            int[] cnt = new int[ALPHA_LEN];
            for (int i = start; i < end; i++) {
                ++cnt[sentence.charAt(i) - 'a'];
            }

            for (int i = 0; i < ALPHA_LEN; i++) {
                if (this.cnt[i] != cnt[i]) return false;
            }
            return true;
        }

        public int getCost(String sentence, int start, int end) {
            if (!isSameAlphabet(sentence, start, end)) return INF; // 불가능한 경우

            int ret = word.length();
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == sentence.charAt(start + i)) --ret;
            }
            return ret;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int n = Integer.parseInt(br.readLine());

        Word[] words = new Word[n];
        for (int i = 0; i < n; i++) {
            words[i] = new Word(br.readLine());
        }

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                for (Word word : words) {
                    dp[i] = Math.min(dp[i], dp[j] + word.getCost(s, j, i));
                }
            }
        }
        System.out.println(dp[s.length()] == INF ? -1 : dp[s.length()]);
    }
}
