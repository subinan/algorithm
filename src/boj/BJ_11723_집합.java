package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11723_집합 {

    private static class Set {
        private int flag;

        public void add(int x) {
            flag |= (1 << x);
        }

        public void remove(int x) {
            flag &= ~(1 << x);
        }

        public int check(int x) {
            return (flag & (1 << x)) != 0 ? 1 : 0;
        }

        public void toggle(int x) {
            flag ^= (1 << x);
        }

        public void all() {
            flag = (1 << 21) - 1;
        }

        public void empty() {
            flag = 0;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        Set set = new Set();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();

            switch (oper) {
                case "add":
                    set.add(Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    set.remove(Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                   sb.append(set.check(Integer.parseInt(st.nextToken()))).append("\n");
                    break;
                case "toggle":
                    set.toggle(Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    set.all();
                    break;
                case "empty":
                    set.empty();
                    break;
                default:
                    break;
            }
        }

        System.out.print(sb);
    }

}
