package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1461_도서관 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] books = new int[N];
        for (int i = 0; i < N; i++) {
            books[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(books);

        // 걸음 수가 가장 많이 필요한 책을 옮길 때, 가는 길에 연속으로 있는 M개의 책들도 함께 옮긴다.
        // 시작점이 0이므로 양수와 음수는 방향이 다르기 때문에 구분한다.
        int idx = 0;
        int cost = Math.abs(books[0]);
        List<Integer> costs = new ArrayList<>();
        while (idx < N && books[idx] < 0) {
            idx += M;
            costs.add(cost);
            cost = idx < N ? Math.abs(books[idx]) : 0;
        }

        idx = N - 1;
        cost = books[idx] > 0 ? Math.abs(books[idx]) : 0;
        while (idx >= 0 && books[idx] > 0) {
            idx -= M;
            costs.add(cost);
            cost = idx >= 0 ? Math.abs(books[idx]) : 0;
        }

        // 가장 멀리 있는 책은 마지막에 이동하여 왕복하지 않도록 한다.
        Collections.sort(costs);
        int ans = -1 * costs.get(costs.size() - 1);
        for (int c: costs) ans += c * 2;

        System.out.println(ans);
    }

}
