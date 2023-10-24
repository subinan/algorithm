package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1339_단어수학 {
    private static class Info implements Comparable<Info> {
        char alpha;
        int cnt;
        public Info(char alpha) {
            this.alpha = alpha;
            this.cnt = 0;
        }

        @Override
        public int compareTo(Info o) {
            return o.cnt - cnt; // cnt 기준 내림차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 알파벳 정보를 담기 위한 클래스 생성
        Info[] infos = new Info[26];
        for (int i = 0; i < 26; i++) {
            infos[i] = new Info((char) ('A' + i));
        }

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int len = word.length();
            for (int j = 0; j < len; j++) {
                infos[word.charAt(j) - 'A'].cnt += Math.pow(10, (len - j) - 1);
            }
        }

        Arrays.sort(infos);

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += infos[i].cnt * (9 - i);
        }
        System.out.println(answer);
    }
}
