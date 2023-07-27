package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://velog.io/@brighthonor/PS26599 참고
public class BJ_26599_용조련사룰루 {

    private static class Dragon implements Comparable<Dragon> {
        int order;
        int power;

        public Dragon(int order, int power) {
            this.order = order;
            this.power = power;
        }

        @Override
        public int compareTo(Dragon o) {
            return this.power - o.power;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Dragon[] dragons = new Dragon[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dragons[i] = new Dragon(i + 1, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(dragons);

        // 가장 큰 Pi 4개에서 서로 그 차이가 M 이하이기만 하면 전부 YES
        // Pn−2, Pn−3을 먼저 보내고, 나머지를 보낸 후, Pn−1, Pn를 보내면 항상 학살이 일어나지 않음

        boolean flag = false;
        int cnt = 0;
        for (int i = N - 1; i > 0; i--) {
            ++cnt;
            if (dragons[i].power - dragons[i - 1].power > M) {
                flag = true;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();

        if (flag) {
            // 드래곤 중 힘의 차이가 M 이상 차이나는 드래곤이 존재한다면,
            // 가장 힘이 센 4명의 드래곤의 힘 차이가 M 이하인지 확인한다. (그렇다면 이동 가능)
            if (cnt < 4) sb.append("NO");
            else {
                sb.append("YES\n");
                for (int i = N - 3; i >= 0; i--) sb.append(dragons[i].order).append(" ");
                for (int i = N - 2; i < N; i++) sb.append(dragons[i].order).append(" ");
            }
        } else {
            // 모든 드래곤 힘의 차이가 M 이하라면 순서대로 옮긴다.
            sb.append("YES\n");
            for (Dragon d: dragons) sb.append(d.order).append(" ");
        }

        System.out.println(sb);
    }

}
