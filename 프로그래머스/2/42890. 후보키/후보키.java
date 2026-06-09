import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int rowLen = relation.length;
        int colLen = relation[0].length;

        List<Integer> unique = new ArrayList<>();

        for (int mask = 1; mask < (1 << colLen); mask++) {
            Set<String> set = new HashSet<>();
            for (int r = 0; r < rowLen; r++) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < colLen; c++) {
                    if ((mask & (1 << c)) != 0) {
                        sb.append(relation[r][c]).append("/");
                    }
                }
                set.add(sb.toString());
            }

            if (set.size() == rowLen) {
                unique.add(mask);
            }
        }

        List<Integer> cadidate = new ArrayList<>();

        for (int i = 0; i < unique.size(); i++) {
            int current = unique.get(i);
            boolean isPossible = true;

            for (int j = 0; j < unique.size(); j++) {
                if (i == j) continue;

                int other = unique.get(j);

                if ((other & current) == other && Integer.bitCount(other) < Integer.bitCount(current)) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                cadidate.add(current);
            }
        }
        return cadidate.size();
    }
}
/**
 * 중복이 없는 조합을 찾는 문제 같은데?
 * 1~8가지로 만들 수 있는 조합 중에서 만약에 2,3,4 중에 2가 빠지면 유일성이 깨지는 케이스를 어떻게 찾냐의 문제 같은데
 */