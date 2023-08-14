package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_1918_후위표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infix = br.readLine();

        Stack<Character> ops = new Stack<>();

        String postfix = "";
        for (char c: infix.toCharArray()) {
            if (c >= 'A' && c <= 'Z') { // 알파벳은 그대로 문자열에 붙이기
                postfix += c;
            } else { // 연산자가 등장할 경우
                if (c == '(') { // 여는 괄호는 스택에 넣기
                    ops.push(c);
                } else if (c == ')') { // 닫는 괄호가 나오면 여는 괄호가 나올 때까지 pop
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        postfix += ops.pop();
                    }
                    ops.pop(); // ( 제거
                } else { // 그 외 +-*/의 경우에는 현재 연산자보다 우선순위가 높은 연산자('(' 제외)는 문자열에 붙이고 스택에 현재 연산자 넣기
                    while (!ops.isEmpty() && ops.peek() != '(' && isHigherPrior(ops.peek(), c)) {
                        postfix += ops.pop();
                    }
                    ops.push(c);
                }
            }
        }
        while (!ops.isEmpty()) {
            postfix += ops.pop();
        }

        System.out.println(postfix);
    }

    private static boolean isHigherPrior(char op1, char op2) {
        return getPriority(op1) <= getPriority(op2);
    }

    private static int getPriority(char op) {
        if (op == '*' || op == '/') return 1;
        if (op == '+' || op == '-') return 2;
        return 0;
    }
}
