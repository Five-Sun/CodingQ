import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            boolean[] arr = new boolean[N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = i; j <= N; j += i) {
                    arr[j] = !arr[j];
                }
            }

            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (arr[i]) count++;
            }
            System.out.println(count);
        }

    }
}
//n개의 방이 일려로 늘어선 감옥, 벌점이 많은 학생이 구금