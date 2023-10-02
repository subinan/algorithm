package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://jaimemin.tistory.com/1316
public class BJ_17828_문자열화폐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // A로 채우거나(최소) Z로 채울 때(최대) 범위를 만족하지 못하면 종료
        if (X < N || X > N * 26) {
            System.out.println("!");
            return;
        }

        char[] answer = new char[N];
        for (int i = 0; i < N; i++) {
            answer[i] = 'A';
        }

        X -= N;
        for (int i =  N - 1; i >= 0 && X > 0; i--) {
            int tmp = Math.min(X, 25);

            answer[i] += tmp;
            X -= tmp;
        }

        System.out.println(String.valueOf(answer));
    }
}
