package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_1351_무한수열 {

    private static Map<Long, Long> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());

        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        System.out.println(solve(n, p, q));
    }

    private static long solve(long n, int p, int q) {
        if (n == 0) return 1;
        if (map.containsKey(n)) return map.get(n);

        long val = solve(n / p, p, q) + solve(n / q, p, q);
        map.put(n, val); // 시간 복잡도를 위한 값 저장
        return val;
    }
}
