package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14890_경사로 {
    private static final int[][] dir = {{0, 1}, {1, 0}};
    private static int N, L;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (check(i, 0, 0)) {
                ++answer;
            }
            if (check(0, i, 1)) {
                ++answer;
            }
        }
        System.out.println(answer);
    }

    private static boolean check(int r, int c, int d) {
        int prev = map[r][c];
        int cnt = 1;
        boolean needInstall = false; // 경사로 설치 해야하는지 나타내는 변수

        for (int i = 1; i < N; i++) {
            int cur = map[r + dir[d][0] * i][c + dir[d][1] * i];

            if (prev == cur) ++cnt; // 높이가 같다면 개수 증가시키기
            else if (Math.abs(prev - cur) > 1) return false; // 높이 차이가 1 초과라면 불가능
            else {
                if (needInstall) { // 경사로를 설치해야 한다면 설치
                    if (cnt < L) return false;
                    cnt -= L;
                    needInstall = false; // 설치 체크
                }

                if (prev < cur && cnt < L) return false; // 높이가 높아진다면 경사로 설치 여부 체크
                if (prev > cur) needInstall = true; // 높이가 낮아진다면 경사로 변수 체크하기 (추후 설치)

                cnt = 1;
                prev = cur;
            }
        }

        if (needInstall && cnt < L) return false; // 경사로를 설치해야 하지만 길이가 짧다면 실패
        return true;
    }
}
