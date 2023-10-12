package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;

public class BJ_6198_옥상정원꾸미기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        long answer = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() <= h[i]) {
                stack.pop();
            }

            stack.push(h[i]);
            answer += stack.size() - 1; // i번째 옥상을 볼 수 있는 다른 빌딩의 개수
        }
        System.out.println(answer);
    }
}
