import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int r = book_time.length;

        ArrayList<Schedule> list = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            String start = book_time[i][0];
            String end = book_time[i][1];
        
            list.add(new Schedule(start, end));
        }

        list.sort(Comparator.comparingInt(a -> a.start));
        
        PriorityQueue<Schedule> pq = new PriorityQueue<>((a, b) -> {
            return a.end - b.end;
        });

        int time = 0;

        for (int i = 0; i < list.size(); i++) {
            time = list.get(i).start;
            
            while(!pq.isEmpty() && time >= pq.peek().end) {
                pq.poll();
            }
            
            pq.offer(list.get(i));
            answer = Math.max(answer, pq.size());
        }

        return answer;
    }

    static class Schedule {
        int start, end;

        Schedule(String start, String end) {
            this.start = calTime(start);
            this.end = calTime(end) + 10;
        }

        int calTime(String time) {
            return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3,5));
        }
    }
}