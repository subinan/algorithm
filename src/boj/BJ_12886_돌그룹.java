package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_12886_돌그룹 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a  = Integer.parseInt(st.nextToken());
        int b  = Integer.parseInt(st.nextToken());
        int c  = Integer.parseInt(st.nextToken());

        int[] group = {a, b, c};
        int cnt = a + b + c;

        if (cnt % 3 != 0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs(group));

    }

    private static int bfs(int[] group) {
        Queue<int[]> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();

        q.add(group);
        set.add(Arrays.toString(group));

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == cur[1] && cur[1] == cur[2]) return 1;

            for (int i = 0; i < 3; i++) {
                int[] copy = cur.clone();

                int x = copy[i] > copy[(i + 1) % 3] ? (i + 1) % 3 : i;
                int y = copy[i] > copy[(i + 1) % 3] ? i : (i + 1) % 3;

                copy[y] -= copy[x];
                copy[x] = 2 * copy[x];

                String copyStr = Arrays.toString(copy);
                if (set.contains(copyStr)) continue;
                q.add(copy);
                set.add(copyStr);
            }
        }

        return 0;
    }
}