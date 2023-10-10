package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_13335_트럭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int weight = 0;
        int idx = 0;
        Queue<int[]> q = new ArrayDeque<>(); // 다리에 있는 트럭 정보 (무게, 남은 시간)

        while (idx < n || !q.isEmpty()) {
            // 다리를 건넌 트럭 제거
            if (!q.isEmpty() && q.peek()[1] == 0) {
                weight -= q.poll()[0];
            }

            // 트럭이 다리에 올라갈 수 있다면 다리에 올리기
            if (idx < n && weight + a[idx] <= l) {
                q.add(new int[]{a[idx], w});
                weight += a[idx++];
            }

            // 다리에 있는 모든 트럭 시간 1씩 줄이기
            for (int[] truck : q) {
                truck[1]--;
            }

            ++answer;
        }

        System.out.println(answer);
    }
}
