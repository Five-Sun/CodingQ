import java.io.*;
import java.util.*;

public class Solution {

    static class Atom {
        int x, y, d, k;
        Atom(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    // 공식 방향 (중요)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());
            Queue<Atom> q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                // 좌표 2배
                q.offer(new Atom((x + 1000) * 2, (y + 1000) * 2, d, k));
            }

            int answer = 0;
            Map<Long, List<Atom>> map = new HashMap<>();

            for (int t = 0; t <= 4000 && !q.isEmpty(); t++) {
                map.clear();

                while (!q.isEmpty()) {
                    Atom a = q.poll();
                    a.x += dx[a.d];
                    a.y += dy[a.d];

                    if (a.x < 0 || a.x > 4000 || a.y < 0 || a.y > 4000) continue;

                    long key = (((long) a.x) << 32) | (a.y & 0xffffffffL);
                    map.computeIfAbsent(key, v -> new ArrayList<>()).add(a);
                }

                for (List<Atom> list : map.values()) {
                    if (list.size() > 1) {
                        for (Atom a : list) answer += a.k;
                    } else {
                        q.offer(list.get(0));
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }
}
