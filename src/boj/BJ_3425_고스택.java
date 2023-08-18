package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ_3425_고스택 {
    private static List<String> cmdList;
    private static GoStack goStack;
    private static class Node {
        long data;
        Node link;

        public Node(long data, Node link) {
            this.data = data;
            this.link = link;
        }
    }

    private static class GoStack {
        private Node top;
        private int size;

        public void num(long x) {
            top = new Node(x, top);
            ++size;
        }

        public long pop() {
            if (size == 0) throw new RuntimeException();
            long n = top.data;
            top = top.link;
            --size;
            return n;
        }

        public void inv() {
            if (size == 0) throw new RuntimeException();
            top.data = -top.data;
        }

        public void dup() {
            if (size == 0) throw new RuntimeException();
            num(top.data);
        }

        public void swp() {
            if (size < 2) throw new RuntimeException();
            long n1 = pop();
            long n2 = pop();
            num(n1);
            num(n2);
        }

        public void add() {
            if (size < 2) throw new RuntimeException();
            long n1 = pop();
            long n2 = pop();
            num(n1 + n2);
        }

        public void sub() {
            if (size < 2) throw new RuntimeException();
            long n1 = pop();
            long n2 = pop();
            num(n2 - n1);
        }

        public void mul() {
            if (size < 2) throw new RuntimeException();
            long n1 = pop();
            long n2 = pop();
            num(n1 * n2);
        }

        public void div() {
            if (size < 2) throw new RuntimeException();
            long n1 = pop();
            long n2 = pop();
            if (n1 == 0) throw new RuntimeException();
            num(n2 / n1);
        }

        public void mod() {
            if (size < 2) throw new RuntimeException();
            long n1 = pop();
            long n2 = pop();
            if (n1 == 0) throw new RuntimeException();
            num(n2 % n1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String cmd = br.readLine();

            if (cmd.equals("QUIT")) break;

            cmdList = new ArrayList<>();

            while (!cmd.equals("END")) {
                cmdList.add(cmd);
                cmd = br.readLine();
            }

            int T = Integer.parseInt(br.readLine());
            while (T-- > 0) {
                int n = Integer.parseInt(br.readLine());
                goStack = new GoStack();

                if (runProgram(n)) {
                    long num = goStack.pop();
                    sb.append(Math.abs(num) <= (int)1e9 ? num : "ERROR").append("\n");
                } else {
                    sb.append("ERROR\n");
                }
            }
            sb.append("\n");
            br.readLine();
        }
        System.out.print(sb);
    }

    private static boolean runProgram(int n) {
        try {
            goStack.num(n);
            for (String cmd : cmdList) {
                String[] cmds = cmd.split(" ");
                switch (cmds[0]) {
                    case "NUM":
                        goStack.num(Integer.parseInt(cmds[1]));
                        break;
                    case "POP":
                        goStack.pop();
                        break;
                    case "INV":
                        goStack.inv();
                        break;
                    case "DUP":
                        goStack.dup();
                        break;
                    case "SWP":
                        goStack.swp();
                        break;
                    case "ADD":
                        goStack.add();
                        break;
                    case "SUB":
                        goStack.sub();
                        break;
                    case "MUL":
                        goStack.mul();
                        break;
                    case "DIV":
                        goStack.div();
                        break;
                    case "MOD":
                        goStack.mod();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return goStack.size == 1;
    }
}