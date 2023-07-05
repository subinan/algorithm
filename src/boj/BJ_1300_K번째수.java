package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1300_K번째수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        System.out.println(binarySearch(N, k));
    }

    private static long binarySearch(int N, int k) {
    	long start = 1;
    	long end = k;
        long mid = (start + end) / 2;

        while (start < end) {
        	long f = find(N, mid);
            if (f < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
            mid = (start + end) / 2;
        }

        return mid;
    }

    private static long find(int N, long mid) {
        long cnt = 0;
        for (int i = 1; i <= N; i++) {
            cnt += Math.min(mid / i, N);
        }
        return cnt;
    }

}
