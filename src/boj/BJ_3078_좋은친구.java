package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3078_좋은친구 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String[] name = new String[N];
        for (int i = 0; i < N; i++) {
            name[i] = br.readLine();
        }

        // K 범위의 친구의 이름 길이 저장할 배열
        // 친구 쌍을 중복으로 세는 것을 방지하기 위해, 등수가 낮은 친구만 센다.
        int[] cnt = new int[21];
        for (int i = 0; i <= K && i < N; i++) {
            ++cnt[name[i].length()];
        }

        long answer = 0;
        for (int i = 0; i < N; i++) {
            --cnt[name[i].length()]; // 현재 이름 제거
            answer += cnt[name[i].length()];

            // K 범위 안이라면 이름 길이 카운트 증가
            if (i + K + 1 < N) ++cnt[name[i + K + 1].length()];

        }

        System.out.println(answer);
    }

}
