package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_1935_후위표기식2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String postfix = br.readLine();

        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        System.out.printf("%.2f\n", calc(postfix, num));
    }

    private static double calc(String postfix, int[] num) {
        Stack<Double> stack = new Stack<>();
        for (char c: postfix.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                stack.push((double) num[c - 'A']);
            } else {
                double n2 = stack.pop();
                double n1 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(n1 + n2);
                        break;
                    case '-':
                        stack.push(n1 - n2);
                        break;
                    case '*':
                        stack.push(n1 * n2);
                        break;
                    case '/':
                        stack.push(n1 / n2);
                        break;
                    default:
                        break;
                }
            }
        }
        return stack.pop();
    }
}
