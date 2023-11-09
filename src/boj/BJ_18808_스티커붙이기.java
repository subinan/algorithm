package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_18808_스티커붙이기 {

    private static int N, M, K;
    private static boolean[][] laptop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        laptop = new boolean[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            boolean[][] sticker = new boolean[r][c];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    if (st.nextToken().equals("1")) sticker[i][j] = true;
                }
            }
            checkSticker(sticker);
        }

        System.out.println(countFill());
    }

    private static void checkSticker(boolean[][] sticker) {
        for (int d = 0; d < 4; d++) {
            int r = sticker.length;
            int c = sticker[0].length;

            for (int i = 0; i <= N - r; i++) {
                for (int j = 0; j <= M - c; j++) {
                    if (isFit(i, j, sticker)) {
                        fillSticker(i, j, sticker);
                        return;
                    }
                }
            }
            sticker = rotate(sticker);
        }
    }

    private static boolean isFit(int r, int c, boolean[][] sticker) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] & laptop[i + r][j + c]) return false;
            }
        }
        return true;
    }

    private static void fillSticker(int r, int c, boolean[][] sticker) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j]) laptop[i + r][j + c] = true;
            }
        }
    }

    private static boolean[][] rotate(boolean[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        boolean[][] newSticker = new boolean[c][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newSticker[j][r - i - 1] = sticker[i][j];
            }
        }
        return newSticker;
    }

    private static int countFill() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (laptop[i][j]) ++count;
            }
        }
        return count;
    }

}
