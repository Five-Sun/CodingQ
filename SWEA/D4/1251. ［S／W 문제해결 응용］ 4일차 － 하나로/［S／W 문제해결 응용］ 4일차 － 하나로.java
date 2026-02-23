import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static double E;
    static int[][] arr;
    static List<List<Node>> graph;
    static class Node {
        int v;
        double w;

        Node (int v, double w) {
            this.v = v;
            this.w = w;
        }
    }

    static void addEdge(int a, int b, double w) {
        graph.get(a).add(new Node(b, w));
        graph.get(b).add(new Node(a, w));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[N][2];

            // X좌표 수집
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());
            }
            // Y좌표 수집
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            //정점 그래프 초기화
            graph = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    int x1 = arr[i][0];
                    int y1 = arr[i][1];

                    int x2 = arr[j][0];
                    int y2 = arr[j][1];

                    double w = getDist(x1, y1, x2, y2);
                    addEdge(i, j, w);
                }
            }

            boolean[] visited = new boolean[N];
            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(a.w, b.w));
            pq.offer(new Node(0, 0));
            long mstCost = 0;
            int count = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if(visited[cur.v]) continue;

                visited[cur.v] = true;
                mstCost += cur.w;
                count++;

                if(count == N) break;

                for (Node next : graph.get(cur.v)) {
                    if (!visited[next.v]) {
                        pq.offer(next);
                    }
                }
            }
            long answer = Math.round(E * mstCost);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb.toString());
    }

    // 거리 구하는 메소드, 제곱근하지 말고 그대로 하는게 핵심!
    static double getDist(int x1, int y1, int x2, int y2) {
        long dx = (long) x2 - x1;
        long dy = (long) y2 - y1;
        return dx * dx + dy * dy;
    }

}
//1. 최소비용트리 문제 같음
//2. 환경 부담 세율 E, 해저터널 길이 L -> E * L ^ 2 만큼 지불
//3. 이것을 최소화하면서 모든 섬이 연결될 수 있는 시스템을 설계하시오.
//4. 입력 T, N, X, Y E
//5. 거리 구해야할 듯
//6. MST, 거리가 곧 비용인 느낌, 근데 각 정점별로 거리가 다 달라진다.
//7. 모든 정점간의 거리를 다 구해야하는 걸까?

