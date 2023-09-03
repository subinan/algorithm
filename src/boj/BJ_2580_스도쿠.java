package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2580_스도쿠 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] sudoku = new int[9][9];
        int zero = 0;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if (sudoku[i][j] == 0) ++zero;
            }
        }

        fillSudoku(zero, sudoku);
    }

    private static void fillSudoku(int zero, int[][] sudoku) {
        if (zero == 0) {
            print(sudoku);
            System.exit(0);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    for (int n = 1; n <= 9; n++) {
                        if (check(i, j, n, sudoku)) {
                            sudoku[i][j] = n;
                            fillSudoku(zero - 1, sudoku);
                            sudoku[i][j] = 0;
                        }
                    }
                    return;
                }
            }
        }
    }

    private static boolean check(int r, int c, int n, int[][] sudoku) {
        // 가로, 세로 확인
        for (int i = 0; i < 9; i++) {
            if (sudoku[r][i] == n || sudoku[i][c] == n) return false;
        }

        int sr = r / 3 * 3;
        int sc = c / 3 * 3;
        // 3x3 확인
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoku[i][j] == n) return false;
            }
        }

        return true;
    }

    private static void print(int[][] sudoku) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}