package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://gaegosoo.tistory.com/99
public class BJ_2479_경로찾기 {

    static class Code {
        private String binaryCode;

        public Code(String binaryCode) {
            this.binaryCode = binaryCode;
        }

        public int getHammingDist(Code code) {
            int cnt = 0;
            String oBinaryCode = code.binaryCode;
            for (int i = 0; i < binaryCode.length(); i++) {
                if (binaryCode.charAt(i) != oBinaryCode.charAt(i)) ++cnt;
            }
            return cnt;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer>[] adj = new ArrayList[n + 1];
        Code[] codes = new Code[n + 1];
        for (int i = 1; i <= n; i++) {
            codes[i] = new Code(br.readLine());
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++){
            for(int j = i + 1; j <= n; j++) {
                int hammingDist = codes[i].getHammingDist(codes[j]);
                if(hammingDist == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bfs(adj, start, end);
    }

    private static void bfs(List<Integer>[] adj, int start, int end) {
        int n = adj.length;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] prev = new int[n + 1];

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int curNum = q.poll();
            if (curNum == end){
                getPath(prev, end);
                return;
            }

            for (int i = 0; i < adj[curNum].size(); i++) {
                int next = adj[curNum].get(i);
                if (visited[next]) continue;
                visited[next] = true;
                q.add(next);
                prev[next] = curNum;
            }
        }

        System.out.println(-1);
    }

    public static void getPath(int[] prev, int dest){
        Stack<Integer> q = new Stack<>();
        while (dest != 0) {
            q.add(dest);
            dest = prev[dest];
        }
        while(!q.isEmpty()) {
            System.out.print(q.pop() + " ");
        }
    }


}