import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Node>> list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(node1).add(new Node(node2, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            return a.cost - b.cost;
        });

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[A] = 0;
        pq.add(new Node(A, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curPoint = cur.point;
            int curCost = cur.cost;

            if (curCost > dist[curPoint]) {
                continue;
            }

            for (Node neighbor : list.get(curPoint)) {
                int newDist = curCost + neighbor.cost;

                if (newDist < dist[neighbor.point]) {
                    dist[neighbor.point] = newDist;
                    pq.add(new Node(neighbor.point, newDist));
                }
            }
        }
        System.out.println(dist[B]);
    }

    static class Node {
        int point;
        int cost;

        public Node(int point, int cost) {
            this.point = point;
            this.cost = cost;
        }
    }
}