package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_4716_풍선 {

    private static class Team implements Comparable<Team> {
        int count;
        int distanceA;
        int distanceB;

        public Team(int count, int distanceA, int distanceB) {
            this.count = count;
            this.distanceA = distanceA;
            this.distanceB = distanceB;
        }

        private int getDiff() {
            return Math.abs(distanceA - distanceB);
        }

        @Override
        public int compareTo(Team o) {
            return Integer.compare(o.getDiff(), getDiff());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Team[] teams = new Team[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                teams[i] = new Team(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(teams); // 방 사이의 거리의 차가 큰 것부터 풍선 나르기

            int answer = 0;
            for (Team t : teams) {
                if (t.distanceA < t.distanceB) { // a가 가까울 경우 방 a에서 풍선 나르기
                    if (a >= t.count) {
                        answer += t.count * t.distanceA;
                        a -= t.count;
                    } else { // 풍선이 부족하다면 나머지는 b에서
                        answer += a * t.distanceA;
                        t.count -= a;
                        a = 0;

                        answer += t.count * t.distanceB;
                        b -= t.count;
                    }
                } else { // b가 가까울 경우 방 b에서 풍선 나르기
                    if (b >= t.count) {
                        answer += t.count * t.distanceB;
                        b -= t.count;
                    } else { // 풍선이 부족하다면 나머지는 a에서
                        answer += b * t.distanceB;
                        t.count -= b;
                        b = 0;

                        answer += t.count * t.distanceA;
                        a -= t.count;
                    }
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }

}
