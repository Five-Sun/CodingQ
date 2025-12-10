import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ropes);
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, ropes[i] * (N - i));
        }
        System.out.println(max);
    }
}
//N개의 로프 중에서 K개를 선택해서 최대 중량 W를 들어야하는데
//나눠서 들 수 가 있어진다
//W / K 가 제일 작은 로프보다 크면 되는건데
