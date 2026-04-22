import java.util.*;

class Solution {
    static boolean[] used;
    static String[] answer;
    static List<Ticket> list;
    static List<String> path;
    static boolean found;

    static class Ticket implements Comparable<Ticket>{
        String from, to;

        Ticket(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Ticket o) {
            if (!this.from.equals(o.from)) return this.from.compareTo(o.from);
            return this.to.compareTo(o.to);
        }
    }
    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        used = new boolean[n];
        answer = new String[n + 1];
        list = new ArrayList<>();
        path = new ArrayList<>();
        found = false;

        for (int i = 0; i < n; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];

            list.add(new Ticket(from, to));
        }
        Collections.sort(list);

        path.add("ICN");

        dfs("ICN", 0);

        return answer;
    }

    static void dfs(String current, int usedCount) {
        if (found) return;

        if (usedCount == list.size()) {
            for (int i = 0; i < path.size(); i++) {
                answer[i] = path.get(i);
            }
            found = true;
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (used[i]) continue;
            if (!list.get(i).from.equals(current)) continue;

            used[i] = true;
            path.add(list.get(i).to);

            dfs(list.get(i).to, usedCount + 1);
            if(found) return;
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
/**
 * 1. 티켓은 알파벳 순서별로 경로를 정렬한다.
 * 2. 최종 경로로 기록할 리스트가 필요하다.
 * 3. 현재 출발지와 갈 수 있는 요소가 아니면 통과
 * 4. 이미 티켓을 썼는지도 체크, 위치 기준이 아니라 티켓 기준이어야 함.
 */