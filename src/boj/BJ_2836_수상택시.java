package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://sa11k.tistory.com/62
public class BJ_2836_수상택시 {

    private static class Taxi implements Comparable<Taxi> {
        int start;
        int end;

        public Taxi(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Taxi o) {
            int comp = Integer.compare(end, o.end);
            if (comp == 0) return Integer.compare(start, o.start);
            return comp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Taxi> toBack = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 역주행 정보만 저장
            if (start > end) toBack.add(new Taxi(start, end));
        }

        Collections.sort(toBack);

        // 한 번에 왔다갔다 할 수 있는 범위
        long minDis = toBack.get(0).end;
        long maxDis = toBack.get(0).start;

        long len = m;

        for (int i = 1; i < toBack.size(); i++) {
            int start = toBack.get(i).end;
            int end = toBack.get(i).start;

            // 승객의 출발지가 한 번에 왔다갔다 할 수 있는 범위 내에 있다면
            // 그 승객의 목적지부터 역주행 가능하기 때문에 변수 갱신
            if (start <= maxDis) maxDis = Math.max(maxDis, end);
            else {
                // 왔다갔다 거리 결과에 추가
                len += 2 * (maxDis - minDis);
                // 새로운 범위 갱신
                minDis = start;
                maxDis = end;
            }
        }
        len += 2 * (maxDis - minDis);

        System.out.println(len);
    }

}
