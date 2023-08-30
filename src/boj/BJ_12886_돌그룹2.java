package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_12886_돌그룹2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a  = Integer.parseInt(st.nextToken());
        int b  = Integer.parseInt(st.nextToken());
        int c  = Integer.parseInt(st.nextToken());

        int[] group = {a, b, c};
        int total = a + b + c;

        if (total % 3 != 0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs(group, total));

    }

    private static int bfs(int[] group, int total) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[total + 1][total + 1];

        q.add(group);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == cur[1] && cur[1] == cur[2]) return 1;

            for (int i = 0; i < 3; i++) {
                int[] copy = cur.clone();

                int x = copy[i] > copy[(i + 1) % 3] ? (i + 1) % 3 : i;
                int y = copy[i] > copy[(i + 1) % 3] ? i : (i + 1) % 3;

                copy[y] -= copy[x];
                copy[x] = 2 * copy[x];

                if (visited[copy[0]][copy[1]]) continue;
                q.add(copy);
                visited[copy[0]][copy[1]] = true;
            }
        }

        return 0;
    }
}
