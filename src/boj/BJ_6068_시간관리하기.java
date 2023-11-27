package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_6068_시간관리하기 {

    public static class Info implements Comparable<Info> {
        int s;
        int t;

        public Info(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Info o) {
            return o.t - t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Info[] infos = new Info[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            infos[i] = new Info(s, t);
        }
        
        Arrays.sort(infos);

        int time = infos[0].t;
        for (Info info : infos) {
            if (time < info.t) info.t = time;
            time = info.t - info.s;
            if (time < 0) {
                time = -1;
                break;
            }
        }

        System.out.println(time);
    }

}
