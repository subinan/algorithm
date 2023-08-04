package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ_2870_수학숙제 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<String> numList = new ArrayList<>();

        while (N-- > 0) {
            char[] input = br.readLine().toCharArray();
            StringBuilder num = new StringBuilder();
            for (char c : input) {
                if (c >= '0' && c <= '9') {
                    if (num.length() == 1 && num.charAt(0) == '0') num.deleteCharAt(0);
                    num.append(c);
                } else {
                    if (num.length() > 0) numList.add(num.toString());
                    num.setLength(0);
                }
            }
            if (num.length() > 0) numList.add(num.toString());
        }

        Collections.sort(numList, (o1, o2) -> {
            int comp1 = o1.length() - o2.length(); // 자릿수 비교
            if (comp1 == 0) {
                return o1.compareTo(o2); // 사전순 비교
            }
            return comp1;
        });

        StringBuilder sb = new StringBuilder();
        for (String num : numList) {
            sb.append(num).append("\n");
        }
        System.out.print(sb);
    }

}
