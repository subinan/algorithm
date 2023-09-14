package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_20440_니가싫어싫어너무싫어싫어오지마내게찝쩍대지마 {
    private static class Time implements Comparable<Time> {
        int s;
        int e;
        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }
        @Override
        public int compareTo(Time o) {
            int comp = Integer.compare(e, o.e);
            if (comp == 0) return Integer.compare(s, o.s);
            return comp;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Time[] T = new Time[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = new Time(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(T, Comparator.comparingInt(t -> t.s));

        int max = 0;
        int s = 0, e = 0;
        PriorityQueue<Time> pq = new PriorityQueue<>();
        for (Time t: T) {
            while (!pq.isEmpty() && t.s > pq.peek().e) {
                pq.poll();
            }
            if (!pq.isEmpty() && t.s == pq.peek().e) {
                if (pq.peek().e == e) e = t.e;
                pq.poll();
            }
            pq.add(t);
            if (pq.size() > max) {
                max = pq.size();
                s = t.s;
                e = pq.peek().e;
            }
        }

        System.out.println(max);
        System.out.println(s + " " + e);
    }
}
