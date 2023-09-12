package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_4920_테트리스게임 {
    private static final int[][][] tetris = {
            {
                    {1, 1, 1, 1}
            },
            {
                    {1, 1, 0},
                    {0, 1, 1}
            },
            {
                    {1, 1, 1},
                    {0, 0, 1}
            },
            {
                    {1, 1, 1},
                    {0, 1, 0}
            },
            {
                    {1, 1},
                    {1, 1}
            }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = 1;

        while (true) {
            int n = Integer.parseInt(br.readLine().trim());
            if (n == 0) break;

            int[][] input = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(t++).append(". ").append(solveTetrisGame(n, input)).append("\n");
        }

        System.out.print(sb);
    }

    private static int solveTetrisGame(int n, int[][] input) {
        int max = Integer.MIN_VALUE; // 블록 하나의 크기는 절댓값 백만까지 들어올 수 있다. 계산하다가 실수할 수 있으니 그냥 최솟값으로..

        for (int i = 0; i < 4; i++) { // 90도 4번 회전 (블록 대신 배열을 돌리면 더 간단하다!!)
            for (int j = 0; j < 5; j++) { // 블록 5개 확인
                for (int r = 0; r <= n - tetris[j].length; r++) {
                    for (int c = 0; c <= n - tetris[j][0].length; c++) {
                        // 해당 블록 영역의 합을 구해서 최댓값 갱신
                        max = Math.max(max, calculateTetrisSum(input, r, c, tetris[j]));
                    }
                }
            }
            input = rotateMatrix(n, input); // 배열 회전
        }

        return max;
    }

    private static int calculateTetrisSum(int[][] input, int r, int c, int[][] tetris) {
        int sum = 0;
        for (int i = 0; i < tetris.length; i++) {
            for (int j = 0; j < tetris[0].length; j++) {
                sum += input[i + r][j + c] * tetris[i][j];
            }
        }
        return sum;
    }

    private static int[][] rotateMatrix(int n, int[][] input) {
        int[][] temp_arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp_arr[i][j] = input[j][n - i - 1];
            }
        }
        return temp_arr;
    }

}
