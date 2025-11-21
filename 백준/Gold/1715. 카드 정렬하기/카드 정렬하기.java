import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while (N-- > 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int sum = 0;
        while (pq.size() > 1) {
            int A = pq.poll() + pq.poll();
            sum += A;
            pq.offer(A);
        }
        System.out.println(sum);
    }
}