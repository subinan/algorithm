package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {
    private static char[][] gear;
    private static int[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gear = new char[4][];
        for (int i = 0; i < 4; i++) {
            gear[i] = br.readLine().toCharArray();
        }

        int K = Integer.parseInt(br.readLine());
        pos = new int[4];
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken()) - 1;
            int rot = Integer.parseInt(st.nextToken());

            // 톱니바퀴 회전
            rotate(no, rot, 1 << no);
        }

        // 네 톱니바퀴의 점수의 합 구하기
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            if (gear[i][pos[i]] == '1') ans += (1 << i);
        }
        System.out.println(ans);
    }

    private static void rotate(int no, int rot, int flag) {

        // 왼쪽 톱니바퀴 확인
        if (no > 0 && (flag & 1 << (no - 1)) == 0 &&
                gear[no - 1][(pos[no - 1] + 2) % 8] != gear[no][(pos[no] + 6) % 8])
            rotate(no - 1, -rot, flag | (1 << (no - 1)));


        // 오른쪽 톱니바퀴 확인
        if (no < 3 && (flag & 1 << (no + 1)) == 0 &&
                gear[no][(pos[no] + 2) % 8] != gear[no + 1][(pos[no + 1] + 6) % 8])
            rotate(no + 1, -rot, flag | (1 << (no + 1)));

        pos[no] += 8 - rot;
        pos[no] %= 8;
    }
}
