import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[N * N];
        int[] CD = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx++;
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);

        long count = 0;
        for (int i = 0; i < N * N; i++) {
            int left = lowerBound(CD, -AB[i]);
            int right = upperBound(CD, -AB[i]);
            count += (right - left);
        }
        System.out.println(count);
    }

    static int lowerBound(int[] CD, int X) {
        int left = 0;
        int right = CD.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (CD[mid] >= X) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    static int upperBound(int[] CD, int X) {
        int left = 0;
        int right = CD.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (CD[mid] > X) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}