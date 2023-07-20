package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BJ_2108_통계학 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] num  = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(num);

        System.out.println(getMean(num));
        System.out.println(getMedian(num));
        System.out.println(getMode(num));
        System.out.println(getRange(num));
    }

    private static long getMean(int[] num) {
        double sum = 0;
        for (int n: num) {
            sum += n;
        }

        return Math.round(sum / num.length);
    }

    private static int getMedian(int[] num) {
        return num[num.length / 2];
    }

    private static int getMode(int[] num) {
        int[] cnt = new int[8001]; // range: -4000 ~ 4000
        int max = 0;

        for (int n: num) {
            ++cnt[n + 4000];
            max = Math.max(max, cnt[n + 4000]);
        }

        List<Integer> maxIdx = new ArrayList<>();
        for (int i = 0; i < 8001; i++) {
            if (cnt[i] == max) maxIdx.add(i - 4000);
        }
        return maxIdx.size() == 1 ? maxIdx.get(0) : maxIdx.get(1);
    }

    private static int getRange(int[] num) {
        return num[num.length - 1] - num[0];
    }

}