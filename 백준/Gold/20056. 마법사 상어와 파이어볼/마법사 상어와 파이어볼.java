import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static Map<String, List<int[]>> map;
    static PriorityQueue<int[]> q;
    //0, 1, 2, 3, 4, 5, 6, 7
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            q.offer(new int[]{r, c, m, s, d});
        }

        map = new HashMap<>();

        while (K-- > 0) {
            //0. map 초기화
            map.clear();

            //1. 파이어볼 움직이기
            move();

            //2. 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
            compute();
        }

        int answer = 0;
        while (!q.isEmpty()) {
            answer += q.poll()[2];
        }

        System.out.println(answer);
    }

    static void move() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int m = cur[2];
            int s = cur[3];
            int d = cur[4];

            int nr = ((r + dx[d] * s) % N + N) % N;
            int nc = ((c + dy[d] * s) % N + N) % N;

            String key = nr + "/" + nc;
            map.computeIfAbsent(key, v -> new ArrayList<>()).add(new int[]{nr, nc, m, s, d});
        }
    }

    static void compute() {
        for (List<int[]> list : map.values()) {
            if (list.size() > 1) {
                int r = list.get(0)[0];
                int c = list.get(0)[1];
                int size = list.size();
                int sSum = 0;
                int mSum = 0;
                int dSum = 0;
                for (int[] fire : list) {
                    mSum += fire[2];
                    sSum += fire[3];
                    if(dCheck(fire[4])) dSum ++;
                }
                int newM = mSum / 5;
                int newS = sSum / size;

                if (newM == 0) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    q.offer(new int[]{r, c, newM, newS, (dSum == size || dSum == 0) ? i * 2 : (i * 2) + 1});
                }
            } else {
                q.offer(list.get(0));
            }
        }
    }

    static boolean dCheck(int d) {
        return d == 0 || d == 2 || d == 4 || d == 6;
    }
}
