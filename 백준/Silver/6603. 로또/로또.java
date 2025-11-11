import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            else {
                int[] numbers = new int[N];
                for (int i = 0; i < N; i++) {
                    numbers[i] = Integer.parseInt(st.nextToken());
                }
                combi(numbers, new boolean[N], N, 0, 0, 6);
            }
            System.out.println();
        }

    }

    public static void combi(int[] num, boolean[] visited, int N, int start, int depth, int r) {
        if (depth == r) {
            for (int i = 0; i < N; i++) {
                if (visited[i]) System.out.print(num[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combi(num, visited, N, i + 1, depth + 1, r);
                visited[i] = false;
            }
        }
    }
}