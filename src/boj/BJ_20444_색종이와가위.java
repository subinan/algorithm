package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20444_색종이와가위 {

    // (x + 1) * (y + 1) = k, x + y = n
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long left = 0, right = n / 2;
        while (left <= right) {
            long x = (left + right) / 2;
            long y = n - x;

            long total = (x + 1) * (y + 1);
            if (total == k) {
                System.out.println("YES");
                return;
            } else if (total > k) {
                right = x - 1;
            } else if (total < k) {
                left = x + 1;
            }
        }

        System.out.println("NO");
    }

}