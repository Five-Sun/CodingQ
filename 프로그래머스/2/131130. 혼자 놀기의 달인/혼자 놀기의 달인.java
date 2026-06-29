class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length];
        int max1 = 0;
        int max2 = 0;

        for (int i = 0; i < cards.length; i++) {
            if (visited[i]) {
                continue;
            }

            int count = 0;
            int current = i;

            while (!visited[current]) {
                visited[current] = true;
                count++;

                current = cards[current] - 1;
            }

            if (count > max1) {
                max2 = max1;
                max1 = count;
            } else if (count > max2) {
                max2 = count;
            }
        }
        return max1 * max2;
    }
}