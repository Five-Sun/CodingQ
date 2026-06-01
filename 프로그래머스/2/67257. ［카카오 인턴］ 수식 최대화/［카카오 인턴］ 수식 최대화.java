import java.util.*;

class Solution {
    char[] opTypes = {'*', '+', '-'};
    boolean[] vistied = new boolean[3];
    List<char[]> permutationList = new ArrayList<>();

    public long solution(String expression) {
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                ops.add(c);
                nums.add(Long.parseLong(sb.toString()));
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        nums.add(Long.parseLong(sb.toString()));

        //우선순위 계산
        permutations(new char[3], 0);

        long maxResult = 0;
        for (char[] priority : permutationList) {
            long result = calculate(nums, ops, priority);
            maxResult = Math.max(maxResult, result);
        }

        return maxResult;
    }

    private void permutations(char[] current, int depth) {
        if (depth == 3) {
            permutationList.add(current.clone());
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!vistied[i]) {
                vistied[i] = true;
                current[depth] = opTypes[i];
                permutations(current, depth + 1);
                vistied[i] = false;
            }
        }
    }

    private long calculate(List<Long> origNums, List<Character> origOps, char[] priority) {
        List<Long> nums = new ArrayList<>(origNums);
        List<Character> ops = new ArrayList<>(origOps);

        for (char op : priority) {
            for (int i = 0; i < ops.size(); ) {
                if (ops.get(i) == op) {
                    long n1 = nums.get(i);
                    long n2 = nums.get(i + 1);
                    long res = 0;
                    if (op == '+') res = n1 + n2;
                    else if (op == '-') res = n1 - n2;
                    else if (op == '*') res = n1 * n2;

                    nums.set(i, res);
                    nums.remove(i + 1);
                    ops.remove(i);
                } else {
                    i++;
                }
            }
        }

        return Math.abs(nums.get(0));
    }

}
/**
 * +, -, * 를 우선순위를 정한다.
 *
 */