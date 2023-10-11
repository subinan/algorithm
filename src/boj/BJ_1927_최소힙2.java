package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BJ_1927_최소힙2 {
    private static class MinHeap {
        private ArrayList<Integer> heap;

        public MinHeap() {
            heap = new ArrayList<>();
            heap.add(0);
        }

        public void add(int x) {
            heap.add(x); // 맨 뒤에 삽입

            // 부모 노드와 비교해서 작다면 교환
            int idx = heap.size() - 1;
            while (idx > 1 && heap.get(idx / 2) > heap.get(idx)) {
                int tmp = heap.get(idx / 2);
                heap.set(idx / 2, heap.get(idx));
                heap.set(idx, tmp);

                idx /= 2; // 부모로 이동
            }
        }

        public int poll() {
            int poll = heap.get(1);

            // 마지막 노를 루트로 옮긴 후 제거
            int last = heap.size() - 1;
            heap.set(1, heap.get(last));
            heap.remove(last);

            int pos = 1;
            while ((pos * 2) < heap.size()) { // 자식과 비교
                // 좌우 자식 중 작은 것 선택
                int min = heap.get(pos * 2);
                int minPos = pos * 2;
                if ((pos * 2 + 1) < heap.size() &&
                        min > heap.get(pos * 2 + 1)) {
                    min = heap.get(pos * 2 + 1);
                    minPos = pos * 2 + 1;
                }

                // 부모가 더 작아지면 종료
                if (min > heap.get(pos)) break;

                // 부모와 자식 교환
                int tmp = heap.get(pos);
                heap.set(pos, heap.get(minPos));
                heap.set(minPos, tmp);
                pos = minPos;
            }
            return poll;
        }

        public boolean isEmpty() {
            return heap.size() == 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        MinHeap heap = new MinHeap();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (heap.isEmpty()) sb.append("0\n");
                else sb.append(heap.poll()).append("\n");
            } else {
                heap.add(x);
            }
        }

        System.out.print(sb);
    }
}
