package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1713_후보추천하기 {
    private static class Picture {
        int student;
        int referrals;
        int postOrder;

        public Picture(int student, int referrals, int postOrder) {
            this.student = student;
            this.referrals = referrals;
            this.postOrder = postOrder;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Picture> pictures = new ArrayList<>();
        int order = 0; // 게시 순서

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int pick = Integer.parseInt(st.nextToken());
            int idx = findIdx(pictures, pick);
            if (idx == -1) { // 게시되지 않은 학생이라면
                // 사진이 꽉 찼다면 추천 수가 가장 적은 사진 제거 (여러 개라면 가장 오래된)
                if (pictures.size() == N) removePicture(pictures);

                // 사진 추가
                pictures.add(new Picture(pick, 1, order++));
            } else { // 이미 사진이 게시되어 있다면
                ++pictures.get(idx).referrals;
            }
        }

        // 학생 번호 순으로 정렬
        pictures.sort(Comparator.comparingInt(p -> p.student));
        StringBuilder sb = new StringBuilder();
        for (Picture picture: pictures) {
            sb.append(picture.student).append(" ");
        }
        System.out.println(sb);
    }

    private static void removePicture(List<Picture> pictures) {
        pictures.sort((p1, p2) -> p1.referrals == p2.referrals ?
                p1.postOrder - p2.postOrder : p1.referrals - p2.referrals);
        pictures.remove(0);
    }

    private static int findIdx(List<Picture> pictures, int pick) {
        for (int i = 0; i < pictures.size(); i++) {
            if (pictures.get(i).student == pick) return i;
        }
        return -1;
    }

}
