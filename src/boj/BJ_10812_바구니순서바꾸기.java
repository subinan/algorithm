package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10812_바구니순서바꾸기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] basket = new int[N];
        for (int i = 0; i < N; i++) {
            basket[i] = i + 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken()) - 1;

            basket = moveBasket(basket, s, e, m);
        }

        StringBuilder sb = new StringBuilder();
        for (int b: basket) sb.append(b).append(" ");
        System.out.println(sb);
    }

    private static int[] moveBasket(int[] basket, int s, int e, int m) {
        int[] newBasket = Arrays.copyOf(basket, basket.length);

        int idx = s;
        for (int i = m; i <= e; i++) newBasket[idx++] = basket[i];
        for (int i = s; i < m; i++) newBasket[idx++] = basket[i];

        return newBasket;
    }

}
