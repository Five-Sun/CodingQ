import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        while (N-- > 0) {
            int num = Integer.parseInt(br.readLine());

            maxHeap.offer(num);

            if (maxHeap.size() - minHeap.size() > 1) {
                minHeap.offer(maxHeap.poll());
                System.out.println(maxHeap.peek());
                continue;
            }

            if (!minHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {
                    int temp = maxHeap.poll();
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(temp);
                }
            }
            System.out.println(maxHeap.peek());
        }

    }
}
//최대 힙에 우선 넣어.
//그리고 갯수를 비교하면서 옮겨야 하나?
//최소힙보다 최대힙의 정상값이 크면 옮겨야 함
//최소힙은 어떻게 넣냐? size() 2 이상 차이나면 문제가 됨