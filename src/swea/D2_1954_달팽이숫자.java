package swea;

import java.util.Scanner;

public class D2_1954_달팽이숫자 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int tc = sc.nextInt();

        // 우, 하, 좌, 상
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        for (int t = 1; t <= tc; t++) {
            int n = sc.nextInt();
            int[][] snail = new int[n][n];

            int r = 0, c = 0; // 현재 행렬 인덱스
            int d = 0; // 우하좌상 선택
            snail[0][0] = 1; // 초기화
            int cnt = 2; // snail 번호
            while (cnt <= n * n) { // snail에 숫자를 다 채울 때까지
                int nr = r + dr[d]; // 다음 행 인덱스
                int nc = c + dc[d]; // 다음 열 인덱스

                // 배열을 벗어나거나 이미 방문한 snail이면
                if (nr < 0 || nr >= n || nc < 0 || nc >= n
                        || snail[nr][nc] != 0) {
                    d = (d + 1) % 4; // 우->하->좌->상 변경
                    continue;
                }

                r = nr;
                c = nc;
                snail[r][c] = cnt++; // snail에 번호를 대입하고 cnt 증가
            }

            // 출력
            sb.append("#" + t + "\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(snail[i][j] + " ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
        sc.close();
    }
}
