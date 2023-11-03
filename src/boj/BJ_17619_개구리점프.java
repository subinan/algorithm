package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17619_개구리점프 {

    private static int[] parents;

    private static class Info implements Comparable<Info> {
        int x1;
        int x2;
        int y;
        int no;

        public Info(int x1, int x2, int y, int no) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.no = no;
        }

        @Override
        public int compareTo(Info o) {
            int comp = Integer.compare(x1, o.x1);
            if (comp == 0) return Integer.compare(x2, o.x2);
            return comp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // 입력
        Info[] infos = new Info[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            infos[i] = new Info(x1, x2, y, i);
        }
        Arrays.sort(infos);

        // 유니온 파인드
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        // 범위에 속하면 같은 집합으로 넣어주기
        int e = 1;
        for (int i = 0; i < N; i++) {
            if (i > 0 && infos[i].x1 <= e) {
                union(infos[i - 1].no, infos[i].no);
            }
            e = Math.max(e, infos[i].x2);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            // 같은 집합이면 1, 아니면 0
            if (find(a) == find(b)) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.print(sb);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parents[b] = a;
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return find(parents[a]);
    }

}
