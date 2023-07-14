package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_5430_AC {

    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            LinkedList<Integer> x = new LinkedList<>();

            st = new StringTokenizer(br.readLine(), "[],");
            for (int i = 0; i < n; i++) {
                x.addLast(Integer.parseInt(st.nextToken()));
            }

            AC(p, x);
        }

        System.out.print(sb);
    }

    private static void AC(String p, LinkedList<Integer> x) {
        boolean flag = true;
        for (char c: p.toCharArray()) {
            if (c == 'R') flag = !flag;
            else if (c == 'D') {
                if (x.size() == 0) {
                    sb.append("error\n");
                    return;
                }
                if (flag) {
                    x.removeFirst();
                } else {
                    x.removeLast();
                }
            }
        }

        sb.append("[");
        if (flag) {
            while (!x.isEmpty()) sb.append(x.removeFirst()).append(",");
        } else {
            while (!x.isEmpty()) sb.append(x.removeLast()).append(",");
        }

        if (sb.charAt(sb.length() - 1) == ',') sb.deleteCharAt(sb.length() - 1);
        sb.append("]\n");
    }

}
