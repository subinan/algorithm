package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_4256_트리 {
    static int idx;
    static StringBuilder sb;

    private static class Node {
        int node;
        Node left;
        Node right;
        public Node(int node) {
            this.node = node;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int[] preorder = new int[N];
            for (int i = 0; i < N; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int[] inorder = new int[N];
            for (int i = 0; i < N; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            idx = 0;
            Node bt = getBinaryTree(0, N - 1, preorder, inorder);
            postorder(bt);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void postorder(Node bt) {
        if (bt.left != null) postorder(bt.left);
        if (bt.right != null) postorder(bt.right);
        sb.append(bt.node).append(" ");
    }

    private static Node getBinaryTree(int l, int r, int[] preorder, int[] inorder) {
        if (idx >= preorder.length || l > r) return null;

        Node bt = new Node(preorder[idx]);

        // 인덱스가 범위를 벗어난다면 자식이 존재하지 않음
        int i1 = findIdx(inorder, preorder[idx]);
        if (i1 < l || i1 > r) return bt;

        ++idx;
        bt.left = getBinaryTree(l, i1 - 1, preorder, inorder); // 왼쪽 자식 구하기
        bt.right = getBinaryTree(i1 + 1, r, preorder, inorder); // 오른쪽 자식 구하기
        return bt;
    }

    private static int findIdx(int[] inorder, int node) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == node) return i;
        }
        return -1;
    }
}
