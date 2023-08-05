package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_4659_비밀번호발음하기 {
    private final static String vowels = "aeiou";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String password;

        StringBuilder sb = new StringBuilder();

        while (!(password = br.readLine()).equals("end")) {
            sb.append("<").append(password).append("> is ");
            if (check(password)) {
                sb.append("acceptable.\n");
            } else {
                sb.append("not acceptable.\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean check(String password) {
        int vCnt = 0;
        int seqV = 0, seqC = 0; // 연속 모음과 연속 자음의 숫자를 위한 변수

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            // 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
            if (vowels.indexOf(c) >= 0) {
                vCnt++;
            }

            // 같은 글자가 연속적으로 두 번 오면 안되나, ee와 oo는 허용한다.
            if (i > 0 && c != 'e' && c != 'o' && c == password.charAt(i - 1)) {
                return false;
            }

            // 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
            if (vowels.indexOf(c) >= 0) { // 현재 문자가 모음이라면
                seqC = 0; // 연속 자음 개수 초기화
                ++seqV;
            } else { // 현재 문자가 자음이라면
                seqV = 0; // 연속 모음 개수 초기화
                ++seqC;
            }

            if (seqV >= 3 || seqC >= 3) return false;

        }
        return vCnt > 0;
    }

}
