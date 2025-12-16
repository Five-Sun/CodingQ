import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            for (int j = start; j <= end; j++) {
                arr[j] = num;
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
//총 N개 바구니, 각각의 바구니는 1번부터 N번까지 번호
//매우 많은 공을 가지고 있고
//가장 처음 바구니에는 공이 안들어 있고, 바구니에는 1개만 넣을 수 있다.
//도현이는 M 번 공을 넣을거고
//한번 공을 넣을 때, 공을 넣을 범위를 정하고, 같은 공을 넣는다. 이미 공이 들어 있으면 새걸로 넣는다.
//12345
//33000
//33440
//11110
//12110