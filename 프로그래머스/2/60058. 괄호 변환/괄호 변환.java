import java.util.*;

class Solution {
    public String solution(String p) {
        return convert(p);
    }

    private String convert(String p) {
        if (p.isEmpty()) {
            return "";
        }

        int splitIdx = getBalancedIndex(p);
        String u = p.substring(0, splitIdx + 1);
        String v = p.substring(splitIdx + 1);

        if (isCorrect(u)) {
            return u + convert(v);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(convert(v));
        sb.append(")");

        // 💡 수정된 부분: u의 앞뒤를 제외한 나머지 문자의 괄호 방향 뒤집기
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') {
                sb.append(")");
            } else {
                sb.append("(");
            }
        }

        return sb.toString();
    }

    private boolean isCorrect(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    private int getBalancedIndex(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                return i;
            }
        }
        return s.length() - 1;
    }
}
