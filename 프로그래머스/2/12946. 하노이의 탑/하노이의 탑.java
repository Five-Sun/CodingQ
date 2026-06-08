import java.util.ArrayList;
import java.util.List;

class Solution {
    class Node {
        int from, to;

        Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static List<Node> list;
    public int[][] solution(int n) {
        list = new ArrayList<>();

        hanoi(n, 1, 2, 3);

        int[][] answer = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            answer[i][0] = list.get(i).from;
            answer[i][1] = list.get(i).to;
        }
        return answer;
    }

    private void hanoi(int n, int from, int by, int to) {
        if (n == 1) {
            list.add(new Node(from, to));
            return;
        }

        hanoi(n - 1, from, to, by);

        list.add(new Node(from, to));

        hanoi(n - 1, by, from, to);
    }
}