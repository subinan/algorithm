package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16165_걸그룹마스터준석이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        // 걸그룹 정보 입력받기
        Map<String, String[]> team = new HashMap<>();
        Map<String, String> member = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String group = br.readLine();
            int size = Integer.parseInt(br.readLine());
            String[] members = new String[size];
            for (int j = 0; j < size; j++) {
                members[j] = br.readLine();
                member.put(members[j], group);
            }
            Arrays.sort(members);
            team.put(group, members);
        }

        // 퀴즈 맞추기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String quiz = br.readLine();
            int type = Integer.parseInt(br.readLine());
            if (type == 0) {
                String[] members = team.get(quiz);
                for (String m : members) {
                    sb.append(m).append("\n");
                }
            } else if (type == 1) {
                sb.append(member.get(quiz)).append("\n");
            }
        }

        System.out.print(sb);
    }

}
