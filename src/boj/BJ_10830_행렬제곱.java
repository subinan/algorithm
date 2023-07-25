package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10830_행렬제곱 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        print(matrixSquare(arr, B));
    }

    private static void print(int[][] arr) {
        int len = arr.length;

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                sb.append(arr[r][c] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static int[][] matrixSquare(int[][] arr, long n) {
        if (n == 1) return arr;

        int[][] ret = matrixSquare(arr, n / 2);
        if (n % 2 == 0) {
            return matrixMultiple(ret, ret);
        } else {
            return matrixMultiple(matrixMultiple(ret, ret), arr);
        }
    }

    private static int[][] matrixMultiple(int[][] a, int[][] b) {
        int len = a.length;
        int[][] mul = new int[len][len];

        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                for (int k = 0; k < len; k++) {
                    mul[r][c] += a[r][k] * b[k][c];
                    mul[r][c] %= 1000;
                }
            }
        }
        return mul;
    }

}
