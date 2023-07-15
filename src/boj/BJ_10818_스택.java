package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10818_스택 {

    private static class Node {
        public int data;
        public Node link;

        public Node(int data, Node link) {
            this.data = data;
            this.link = link;
        }
    }

    private static class Stack {
        private Node top;
        private int cnt;

        public void push(int data) {
            top = new Node(data, top);
            ++cnt;
        }

        public int pop() {
            if (empty() == 1) return -1;

            Node popNode = top;
            top = popNode.link;
            popNode.link = null;
            --cnt;
            return popNode.data;
        }

        public int size() {
            return cnt;
        }

        public int empty() {
            if (cnt == 0) return 1;
            else return 0;
        }

        public int top() {
            if (empty() == 1) return -1;
            return top.data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Stack stack = new Stack();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            String op = st.nextToken();

            switch (op) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    System.out.println(stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    System.out.println(stack.empty());
                    break;
                case "top":
                    System.out.println(stack.top());
                    break;
                default:
                    break;
            }
        }
    }

}
