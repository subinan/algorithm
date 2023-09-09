package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_9017_크로스컨트리 {
    private static class Info implements Comparable<Info> {
        int team;
        int score;
        int playerCnt;
        int fifthRecord;
        public Info(int team) {
            this.team = team;
            this.score = 0;
            this.playerCnt = 0;
            this.fifthRecord = 0;
        }

        @Override
        public int compareTo(Info o) {
            int comp = score - o.score;
            if (comp == 0) {
                return fifthRecord - o.fifthRecord;
            }
            return comp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] record = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                record[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(getWinTeam(N, record, getValidTeam(N, record))).append("\n");
        }

        System.out.print(sb);
    }

    private static boolean[] getValidTeam(int n, int[] record) {
        int[] playerCnt = new int[200];
        boolean[] isValid = new boolean[200];

        for (int i = 0; i < n; i++) {
            int idx = record[i] - 1;
            if (++playerCnt[idx] == 6) isValid[idx] = true;
        }
        return isValid;
    }

    private static int getWinTeam(int n, int[] record, boolean[] isValid) {
        Info[] infos = new Info[200];
        for (int i = 0; i < 200; i++) {
            infos[i] = new Info(i + 1);
            // 오름차순 정렬을 위해 점수 계산에서 제외되는 팀은 점수를 최댓값으로 설정
            if (!isValid[i]) infos[i].score = Integer.MAX_VALUE;
        }

        int score = 1;
        for (int i = 0; i < n; i++) {
            int team = record[i] - 1;
            if (!isValid[team]) continue;
            if (infos[team].playerCnt < 4) infos[team].score += score;
            if (++infos[team].playerCnt == 5) infos[team].fifthRecord = score;
            ++score;
        }

        // 우승팀 구하기
        // 점수로 내림차순 정렬 -> 같다면 다섯 번째 선수 기록으로 오름차순 정렬
        Arrays.sort(infos);
        return infos[0].team;
    }
}
